package com.beerlot.api.generated.model;

import java.net.URI;
import java.util.Objects;
import com.beerlot.api.generated.model.BeerPageRequest;
import com.beerlot.api.generated.model.BeerResponse;
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
 * BeerPage
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class BeerPage {

  @JsonProperty("contents")
  
  private List<BeerResponse> contents = null;

  @JsonProperty("page_request")
  private BeerPageRequest pageRequest;

  @JsonProperty("page")
  private Integer page;

  @JsonProperty("total_elements")
  private Integer totalElements;

  @JsonProperty("total_pages")
  private Integer totalPages;

  @JsonProperty("nextPage")
  private Integer nextPage;

  public BeerPage contents(List<BeerResponse> contents) {
    this.contents = contents;
    return this;
  }

  public BeerPage addContentsItem(BeerResponse contentsItem) {
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
  public List<BeerResponse> getContents() {
    return contents;
  }

  public void setContents(List<BeerResponse> contents) {
    this.contents = contents;
  }

  public BeerPage pageRequest(BeerPageRequest pageRequest) {
    this.pageRequest = pageRequest;
    return this;
  }

  /**
   * Get pageRequest
   * @return pageRequest
  */
  @Schema(name = "page_request", required = false)
  public BeerPageRequest getPageRequest() {
    return pageRequest;
  }

  public void setPageRequest(BeerPageRequest pageRequest) {
    this.pageRequest = pageRequest;
  }

  public BeerPage page(Integer page) {
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

  public BeerPage totalElements(Integer totalElements) {
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

  public BeerPage totalPages(Integer totalPages) {
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

  public BeerPage nextPage(Integer nextPage) {
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
    BeerPage beerPage = (BeerPage) o;
    return Objects.equals(this.contents, beerPage.contents) &&
        Objects.equals(this.pageRequest, beerPage.pageRequest) &&
        Objects.equals(this.page, beerPage.page) &&
        Objects.equals(this.totalElements, beerPage.totalElements) &&
        Objects.equals(this.totalPages, beerPage.totalPages) &&
        Objects.equals(this.nextPage, beerPage.nextPage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contents, pageRequest, page, totalElements, totalPages, nextPage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeerPage {\n");
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

