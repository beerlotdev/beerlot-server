resource "google_service_account" "deploy_service_account" {
  account_id   = "deploy"
  display_name = "Deploy"
  description = "Deploy Service Account"
  project      = var.project
  depends_on = [
    google_project_service.apis
  ]
}