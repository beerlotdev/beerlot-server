package com.beerlot.api.generated.model;

import java.net.URI;
import java.util.Objects;
import com.beerlot.api.generated.model.CategorySupResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * CategorySupResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CategorySupResponse {

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("parent")
  private CategorySupResponse parent;

  @JsonProperty("id")
  private Long id;

  public CategorySupResponse name(String name) {
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

  public CategorySupResponse description(String description) {
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

  public CategorySupResponse parent(CategorySupResponse parent) {
    this.parent = parent;
    return this;
  }

  /**
   * Get parent
   * @return parent
  */
  @Schema(name = "parent", required = true)
  public CategorySupResponse getParent() {
    return parent;
  }

  public void setParent(CategorySupResponse parent) {
    this.parent = parent;
  }

  public CategorySupResponse id(Long id) {
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
    CategorySupResponse categorySupResponse = (CategorySupResponse) o;
    return Objects.equals(this.name, categorySupResponse.name) &&
        Objects.equals(this.description, categorySupResponse.description) &&
        Objects.equals(this.parent, categorySupResponse.parent) &&
        Objects.equals(this.id, categorySupResponse.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, parent, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategorySupResponse {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
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

