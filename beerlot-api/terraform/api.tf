resource "google_project_service" "apis" {
  for_each = toset([
    "iam.googleapis.com",
    "run.googleapis.com",
    "artifactregistry.googleapis.com",
    "sqladmin.googleapis.com"
  ])
  project                    = var.project
  service                    = each.key
  disable_dependent_services = true
}