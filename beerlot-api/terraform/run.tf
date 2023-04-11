locals {
  docker_image = "${var.region}-docker.pkg.dev/${var.project}/${google_artifact_registry_repository.beerlot_core_api_repository.repository_id}/beerlot-core-api-image:${var.image_tag}",
}

resource "null_resource" "docker_push" {

  triggers = {
    always_run = timestamp()
  }

  provisioner "local-exec" {
    command = <<END_OF_SCRIPT

      cd ../
      docker build -t ${local.docker_image} -f ${var.docker_file} .
      docker push ${local.docker_image}

    END_OF_SCRIPT
  }

  depends_on = [
    google_artifact_registry_repository.beerlot_core_api_repository
  ]
}

resource "google_cloud_run_service" "beerlot_core_api" {
  name     = beerlot-core-api
  location = var.region

  template {
    spec {
      containers {
        image = local.docker_image
        ports {
          container_port =80
        }
      }
    }

    metadata {
      name = "beerlot-core-api-${var.image_tag}"

      annotations = {
        "run.googleapis.com/cloudsql-instances"   = google_sql_database_instance.beerlot_database_instance.connection_name
        "run.googleapis.com/client-name"          = "terraform"
      }
    }
  }

  depends_on = [
    null_resource.docker_push
  ]
}
