package com.beerlot.api.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ReviewResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ReviewResponse {

  @JsonProperty("content")
  private String content;

  @JsonProperty("rate")
  private Float rate;

  @JsonProperty("created_at")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date createdAt;

  @JsonProperty("updated_at")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date updatedAt;

  @JsonProperty("like_count")
  private Long likeCount;

  @JsonProperty("beer_id")
  private Long beerId;

  @JsonProperty("id")
  private Long id;

  public ReviewResponse content(String content) {
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

  public ReviewResponse rate(Float rate) {
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

  public ReviewResponse createdAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  */
  @Schema(name = "created_at", required = true)
  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public ReviewResponse updatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Get updatedAt
   * @return updatedAt
  */
  @Schema(name = "updated_at", required = false)
  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public ReviewResponse likeCount(Long likeCount) {
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

  public ReviewResponse beerId(Long beerId) {
    this.beerId = beerId;
    return this;
  }

  /**
   * Get beerId
   * @return beerId
  */
  @Schema(name = "beer_id", required = true)
  public Long getBeerId() {
    return beerId;
  }

  public void setBeerId(Long beerId) {
    this.beerId = beerId;
  }

  public ReviewResponse id(Long id) {
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
    ReviewResponse reviewResponse = (ReviewResponse) o;
    return Objects.equals(this.content, reviewResponse.content) &&
        Objects.equals(this.rate, reviewResponse.rate) &&
        Objects.equals(this.createdAt, reviewResponse.createdAt) &&
        Objects.equals(this.updatedAt, reviewResponse.updatedAt) &&
        Objects.equals(this.likeCount, reviewResponse.likeCount) &&
        Objects.equals(this.beerId, reviewResponse.beerId) &&
        Objects.equals(this.id, reviewResponse.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, rate, createdAt, updatedAt, likeCount, beerId, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReviewResponse {\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    likeCount: ").append(toIndentedString(likeCount)).append("\n");
    sb.append("    beerId: ").append(toIndentedString(beerId)).append("\n");
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

