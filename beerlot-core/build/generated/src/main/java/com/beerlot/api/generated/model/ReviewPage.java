package com.beerlot.api.generated.model;

import java.net.URI;
import java.util.Objects;
import com.beerlot.api.generated.model.ReviewPageRequest;
import com.beerlot.api.generated.model.ReviewResponse;
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
 * ReviewPage
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ReviewPage {

  @JsonProperty("contents")
  
  private List<ReviewResponse> contents = null;

  @JsonProperty("page_request")
  private ReviewPageRequest pageRequest;

  @JsonProperty("page")
  private Integer page;

  @JsonProperty("total_elements")
  private Integer totalElements;

  @JsonProperty("total_pages")
  private Integer totalPages;

  @JsonProperty("nextPage")
  private Integer nextPage;

  public ReviewPage contents(List<ReviewResponse> contents) {
    this.contents = contents;
    return this;
  }

  public ReviewPage addContentsItem(ReviewResponse contentsItem) {
    if (this.contents == null) {
      this.contents = new ArrayList<>();
    }
    this.contents.add(contentsItem);
    return this;
  }

  /**
   * Get contents
   * @return contents
  */
  @Schema(name = "contents", required = false)
  public List<ReviewResponse> getContents() {
    return contents;
  }

  public void setContents(List<ReviewResponse> contents) {
    this.contents = contents;
  }

  public ReviewPage pageRequest(ReviewPageRequest pageRequest) {
    this.pageRequest = pageRequest;
    return this;
  }

  /**
   * Get pageRequest
   * @return pageRequest
  */
  @Schema(name = "page_request", required = false)
  public ReviewPageRequest getPageRequest() {
    return pageRequest;
  }

  public void setPageRequest(ReviewPageRequest pageRequest) {
    this.pageRequest = pageRequest;
  }

  public ReviewPage page(Integer page) {
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

  public ReviewPage totalElements(Integer totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Get totalElements
   * @return totalElements
  */
  @Schema(name = "total_elements", required = false)
  public Integer getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Integer totalElements) {
    this.totalElements = totalElements;
  }

  public ReviewPage totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Get totalPages
   * @return totalPages
  */
  @Schema(name = "total_pages", required = false)
  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public ReviewPage nextPage(Integer nextPage) {
    this.nextPage = nextPage;
    return this;
  }

  /**
   * Get nextPage
   * @return nextPage
  */
  @Schema(name = "nextPage", required = false)
  public Integer getNextPage() {
    return nextPage;
  }

  public void setNextPage(Integer nextPage) {
    this.nextPage = nextPage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReviewPage reviewPage = (ReviewPage) o;
    return Objects.equals(this.contents, reviewPage.contents) &&
        Objects.equals(this.pageRequest, reviewPage.pageRequest) &&
        Objects.equals(this.page, reviewPage.page) &&
        Objects.equals(this.totalElements, reviewPage.totalElements) &&
        Objects.equals(this.totalPages, reviewPage.totalPages) &&
        Objects.equals(this.nextPage, reviewPage.nextPage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contents, pageRequest, page, totalElements, totalPages, nextPage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReviewPage {\n");
    sb.append("    contents: ").append(toIndentedString(contents)).append("\n");
    sb.append("    pageRequest: ").append(toIndentedString(pageRequest)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
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

