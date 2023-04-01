resource "google_storage_bucket" "image_bucket" {
  project       = var.project
  location      = var.region

  name          = "images-${var.project}"
  force_destroy = true
  uniform_bucket_level_access = true
}

resource "google_storage_bucket" "image_bucket_subfolders" {
  project       = var.project
  location      = var.region

  for_each = toset(var.image_buckets)
  name          = "${each.key}/"
  bucket        = google_storage_bucket.image_bucket.name
  force_destroy = true
  uniform_bucket_level_access = true
}