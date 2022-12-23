package com.beerlot.api.generated.model;

import java.net.URI;
import java.util.Objects;
import com.beerlot.api.generated.model.BeerSortType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * BeerPageRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class BeerPageRequest {

  @JsonProperty("sort")
  private BeerSortType sort;

  @JsonProperty("page")
  private Integer page;

  @JsonProperty("size")
  private Integer size;

  @JsonProperty("offset")
  private Integer offset;

  public BeerPageRequest sort(BeerSortType sort) {
    this.sort = sort;
    return this;
  }

  /**
   * Get sort
   * @return sort
  */
  @Schema(name = "sort", required = false)
  public BeerSortType getSort() {
    return sort;
  }

  public void setSort(BeerSortType sort) {
    this.sort = sort;
  }

  public BeerPageRequest page(Integer page) {
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

  public BeerPageRequest size(Integer size) {
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

  public BeerPageRequest offset(Integer offset) {
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
    BeerPageRequest beerPageRequest = (BeerPageRequest) o;
    return Objects.equals(this.sort, beerPageRequest.sort) &&
        Objects.equals(this.page, beerPageRequest.page) &&
        Objects.equals(this.size, beerPageRequest.size) &&
        Objects.equals(this.offset, beerPageRequest.offset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sort, page, size, offset);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeerPageRequest {\n");
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

