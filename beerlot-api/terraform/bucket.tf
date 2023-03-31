resource "google_storage_bucket" "static-site" {
  for_each = toset(var.image_buckets)
  name          = "beerlot-images/${each.key}"
  location      = var.region
  force_destroy = true

  uniform_bucket_level_access = true
}