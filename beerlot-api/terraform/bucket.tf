resource "google_storage_bucket" "image_buckets" {
  for_each = toset(var.image_buckets)
  name          = "beerlot-images/${each.key}"
  project = var.project
  location      = var.region
  force_destroy = true

  uniform_bucket_level_access = true

}