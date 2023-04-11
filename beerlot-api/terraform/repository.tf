resource "google_artifact_registry_repository" "beerlot_core_api_repository" {
  project  = var.project
  location = var.region

  repository_id = "beerlot-core-api-repository"
  description   = "Artifact Registry Repository for Core API Image"
  format        = "DOCKER"
}