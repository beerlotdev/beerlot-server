# APIs
resource "google_project_service" "apis" {
  for_each = toset([
    "iam.googleapis.com",
    "run.googleapis.com",
    "artifactregistry.googleapis.com"
  ])
  project                    = var.project
  service                    = each.key
  disable_dependent_services = true
}

# Service Account & IAM
resource "google_service_account" "sa_deploy" {
  account_id   = "deploy-sa"
  display_name = "Service Account for Deploy"
  project      = var.project
  depends_on = [
    google_project_service.apis
  ]
}

resource "google_project_iam_member" "apigee_service_account" {
  project = var.project
  role    = "roles/owner"
  member  = "serviceAccount:deploy-sa@${var.project}.iam.gserviceaccount.com"
  depends_on = [
    google_project_service.apis,
    google_service_account.sa_deploy
  ]
}



# Cloud SQL
resource "google_sql_database_instance" "sql_instance" {
  name             = "beerlot-sql-instance"
  database_version = "POSTGRES_14"
  region           = var.region
  settings {
    tier = "db-f1-micro"
  }
  depends_on = [
    google_project_service.apis
  ]
}

resource "google_sql_database" "database" {
  name     = "beerlot_db"
  instance = google_sql_database_instance.sql_instance.name
}

# Cloud Artifact Registry
resource "google_artifact_registry_repository" "artifact_registry_core" {
  location      = var.region
  repository_id = "core-image-repository"
  description   = "Core Image Repository"
  format        = "DOCKER"
  depends_on = [
    google_project_service.apis
  ]
}

# Cloud Run