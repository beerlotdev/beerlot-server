package com.beerlot.api.generated.model;

import java.net.URI;
import java.util.Objects;
import com.beerlot.api.generated.model.ReviewSortType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ReviewPageRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ReviewPageRequest {

  @JsonProperty("sort")
  private ReviewSortType sort;

  @JsonProperty("page")
  private Integer page;

  @JsonProperty("size")
  private Integer size;

  @JsonProperty("offset")
  private Integer offset;

  public ReviewPageRequest sort(ReviewSortType sort) {
    this.sort = sort;
    return this;
  }

  /**
   * Get sort
   * @return sort
  */
  @Schema(name = "sort", required = false)
  public ReviewSortType getSort() {
    return sort;
  }

  public void setSort(ReviewSortType sort) {
    this.sort = sort;
  }

  public ReviewPageRequest page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Get page
   * @return page
  */
  @Schema(name = "page", required = false)
  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public ReviewPageRequest size(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
  */
  @Schema(name = "size", required = false)
  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public ReviewPageRequest offset(Integer offset) {
    this.offset = offset;
    return this;
  }

  /**
   * Get offset
   * @return offset
  */
  @Schema(name = "offset", required = false)
  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReviewPageRequest reviewPageRequest = (ReviewPageRequest) o;
    return Objects.equals(this.sort, reviewPageRequest.sort) &&
        Objects.equals(this.page, reviewPageRequest.page) &&
        Objects.equals(this.size, reviewPageRequest.size) &&
        Objects.equals(this.offset, reviewPageRequest.offset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sort, page, size, offset);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReviewPageRequest {\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
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

