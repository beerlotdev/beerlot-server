package com.beerlot.api.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ReviewCreateRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ReviewCreateRequest {

  @JsonProperty("content")
  private String content;

  @JsonProperty("rate")
  private Float rate;

  @JsonProperty("image_url")
  private String imageUrl;

  public ReviewCreateRequest content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
  */
  @Schema(name = "content", required = true)
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ReviewCreateRequest rate(Float rate) {
    this.rate = rate;
    return this;
  }

  /**
   * Get rate
   * @return rate
  */
  @Schema(name = "rate", required = true)
  public Float getRate() {
    return rate;
  }

  public void setRate(Float rate) {
    this.rate = rate;
  }

  public ReviewCreateRequest imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * Get imageUrl
   * @return imageUrl
  */
  @Schema(name = "image_url", required = false)
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReviewCreateRequest reviewCreateRequest = (ReviewCreateRequest) o;
    return Objects.equals(this.content, reviewCreateRequest.content) &&
        Objects.equals(this.rate, reviewCreateRequest.rate) &&
        Objects.equals(this.imageUrl, reviewCreateRequest.imageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, rate, imageUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReviewCreateRequest {\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
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

