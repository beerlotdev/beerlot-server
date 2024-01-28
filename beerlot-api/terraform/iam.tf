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
  for_each = toset(var.backend_developers)
  member   = each.key
}

resource "google_project_iam_member" "cloud_run_invoker_for_devs" {
  project  = var.project
  role     = "roles/run.invoker"
  for_each = toset(var.backend_developers)
  member   = each.key
}

resource "google_cloud_run_service_iam_member" "cloud_run_invokers" {
  role = "roles/run.invoker"
  member = google_service_account.beerlot_client_service_account.member

  project = var.project
  location = var.region
  service = google_cloud_run_service.beerlot_core_api.name
}

resource "google_service_account"  "beerlot_client_service_account"{
  account_id   = "beerlot-client"
  display_name = "BeerLot Client"
  description  = "BeerLot Client Service Account"
  project      = var.project
  depends_on = [
    google_project_service.apis
  ]
}

resource "google_project_iam_member" "run_invoker_for_beerlot_client" {
  project  = var.project
  role     = "roles/run.invoker"
  member   = google_service_account.beerlot_client_service_account.member
}
