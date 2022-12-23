package com.beerlot.api.generated.model;

import java.net.URI;
import java.util.Objects;
import com.beerlot.api.generated.model.CategorySupResponse;
import com.beerlot.api.generated.model.TagResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Beer Find Response
 */

@Schema(name = "BeerResponse", description = "Beer Find Response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class BeerResponse {

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("origin_country")
  private String originCountry;

  @JsonProperty("origin_city")
  private String originCity;

  @JsonProperty("volume")
  private Float volume;

  @JsonProperty("image_url")
  private String imageUrl;

  @JsonProperty("category")
  private CategorySupResponse category;

  @JsonProperty("tags")
  
  private List<TagResponse> tags = new ArrayList<>();

  @JsonProperty("like_count")
  private Long likeCount;

  @JsonProperty("review_count")
  private Long reviewCount;

  @JsonProperty("rate")
  private Float rate;

  @JsonProperty("id")
  private Long id;

  public BeerResponse name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @Schema(name = "name", required = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BeerResponse description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @Schema(name = "description", required = true)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BeerResponse originCountry(String originCountry) {
    this.originCountry = originCountry;
    return this;
  }

  /**
   * Get originCountry
   * @return originCountry
  */
  @Schema(name = "origin_country", required = false)
  public String getOriginCountry() {
    return originCountry;
  }

  public void setOriginCountry(String originCountry) {
    this.originCountry = originCountry;
  }

  public BeerResponse originCity(String originCity) {
    this.originCity = originCity;
    return this;
  }

  /**
   * Get originCity
   * @return originCity
  */
  @Schema(name = "origin_city", required = false)
  public String getOriginCity() {
    return originCity;
  }

  public void setOriginCity(String originCity) {
    this.originCity = originCity;
  }

  public BeerResponse volume(Float volume) {
    this.volume = volume;
    return this;
  }

  /**
   * Get volume
   * minimum: 0.0
   * @return volume
  */
  @Schema(name = "volume", required = true)
  public Float getVolume() {
    return volume;
  }

  public void setVolume(Float volume) {
    this.volume = volume;
  }

  public BeerResponse imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * Get imageUrl
   * @return imageUrl
  */
  @Schema(name = "image_url", required = true)
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public BeerResponse category(CategorySupResponse category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  */
  @Schema(name = "category", required = true)
  public CategorySupResponse getCategory() {
    return category;
  }

  public void setCategory(CategorySupResponse category) {
    this.category = category;
  }

  public BeerResponse tags(List<TagResponse> tags) {
    this.tags = tags;
    return this;
  }

  public BeerResponse addTagsItem(TagResponse tagsItem) {
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
  */
  @Schema(name = "tags", required = true)
  public List<TagResponse> getTags() {
    return tags;
  }

  public void setTags(List<TagResponse> tags) {
    this.tags = tags;
  }

  public BeerResponse likeCount(Long likeCount) {
    this.likeCount = likeCount;
    return this;
  }

  /**
   * Get likeCount
   * @return likeCount
  */
  @Schema(name = "like_count", required = true)
  public Long getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(Long likeCount) {
    this.likeCount = likeCount;
  }

  public BeerResponse reviewCount(Long reviewCount) {
    this.reviewCount = reviewCount;
    return this;
  }

  /**
   * Get reviewCount
   * @return reviewCount
  */
  @Schema(name = "review_count", required = true)
  public Long getReviewCount() {
    return reviewCount;
  }

  public void setReviewCount(Long reviewCount) {
    this.reviewCount = reviewCount;
  }

  public BeerResponse rate(Float rate) {
    this.rate = rate;
    return this;
  }

  /**
   * Get rate
   * @return rate
  */
  @Schema(name = "rate", required = false)
  public Float getRate() {
    return rate;
  }

  public void setRate(Float rate) {
    this.rate = rate;
  }

  public BeerResponse id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @Schema(name = "id", required = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeerResponse beerResponse = (BeerResponse) o;
    return Objects.equals(this.name, beerResponse.name) &&
        Objects.equals(this.description, beerResponse.description) &&
        Objects.equals(this.originCountry, beerResponse.originCountry) &&
        Objects.equals(this.originCity, beerResponse.originCity) &&
        Objects.equals(this.volume, beerResponse.volume) &&
        Objects.equals(this.imageUrl, beerResponse.imageUrl) &&
        Objects.equals(this.category, beerResponse.category) &&
        Objects.equals(this.tags, beerResponse.tags) &&
        Objects.equals(this.likeCount, beerResponse.likeCount) &&
        Objects.equals(this.reviewCount, beerResponse.reviewCount) &&
        Objects.equals(this.rate, beerResponse.rate) &&
        Objects.equals(this.id, beerResponse.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, originCountry, originCity, volume, imageUrl, category, tags, likeCount, reviewCount, rate, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeerResponse {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    originCountry: ").append(toIndentedString(originCountry)).append("\n");
    sb.append("    originCity: ").append(toIndentedString(originCity)).append("\n");
    sb.append("    volume: ").append(toIndentedString(volume)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    likeCount: ").append(toIndentedString(likeCount)).append("\n");
    sb.append("    reviewCount: ").append(toIndentedString(reviewCount)).append("\n");
    sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

