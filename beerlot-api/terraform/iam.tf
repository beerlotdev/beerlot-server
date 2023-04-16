resource "google_service_account" "deploy_service_account" {
  account_id   = "deploy"
  display_name = "Deploy"
  description  = "Deploy Service Account"
  project      = var.project
  depends_on = [
    google_project_service.apis
  ]
}

resource "google_project_iam_member" "storage_viewers" {
  project  = var.project
  role     = "roles/viewer"
  for_each = toset(var.project_members)
  member   = each.key
}

resource "google_project_iam_member" "security_admins" {
  project  = var.project
  role     = "roles/iam.securityAdmin"
  for_each = toset(var.project_security_admin)
  member   = each.key
}

resource "google_cloud_run_service_iam_member" "allusers_cloud_run" {
  role = "roles/run.invoker"
  member = "allUsers"

  project = var.project
  location = var.region
  service = google_cloud_run_service.beerlot_core_api.name
}