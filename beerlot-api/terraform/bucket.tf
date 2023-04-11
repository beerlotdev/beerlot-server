resource "google_storage_bucket" "image_bucket" {
  project  = var.project
  location = var.region

  name                        = "images-${var.project}"
  force_destroy               = true
  uniform_bucket_level_access = true
}

resource "google_storage_bucket_object" "image_bucket_subfolders" {
  for_each = toset(var.image_buckets)
  name     = "${each.key}/"
  content  = "Images for ${title(each.key)}"
  bucket   = google_storage_bucket.image_bucket.name
}