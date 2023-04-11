resource "google_iap_brand" "project_brand" {
  support_email     = "beerlot.tech@gmail.com"
  application_title = "BeerLot"
}

resource "google_iap_client" "project_oauth_client" {
  display_name = "BeerLot OAuth Client"
  brand        =  google_iap_brand.project_brand.name
}