package com.beerlot.api.generated.model;

import java.net.URI;
import java.util.Objects;
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
 * TagResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TagResponse {

  @JsonProperty("name_ko")
  private String nameKo;

  @JsonProperty("name_en")
  private String nameEn;

  @JsonProperty("description")
  private String description;

  @JsonProperty("beers")
  
  private List<BeerResponse> beers = null;

  @JsonProperty("id")
  private Long id;

  public TagResponse nameKo(String nameKo) {
    this.nameKo = nameKo;
    return this;
  }

  /**
   * Get nameKo
   * @return nameKo
  */
  @Schema(name = "name_ko", required = true)
  public String getNameKo() {
    return nameKo;
  }

  public void setNameKo(String nameKo) {
    this.nameKo = nameKo;
  }

  public TagResponse nameEn(String nameEn) {
    this.nameEn = nameEn;
    return this;
  }

  /**
   * Get nameEn
   * @return nameEn
  */
  @Schema(name = "name_en", required = true)
  public String getNameEn() {
    return nameEn;
  }

  public void setNameEn(String nameEn) {
    this.nameEn = nameEn;
  }

  public TagResponse description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @Schema(name = "description", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TagResponse beers(List<BeerResponse> beers) {
    this.beers = beers;
    return this;
  }

  public TagResponse addBeersItem(BeerResponse beersItem) {
    if (this.beers == null) {
      this.beers = new ArrayList<>();
    }
    this.beers.add(beersItem);
    return this;
  }

  /**
   * Get beers
   * @return beers
  */
  @Schema(name = "beers", required = false)
  public List<BeerResponse> getBeers() {
    return beers;
  }

  public void setBeers(List<BeerResponse> beers) {
    this.beers = beers;
  }

  public TagResponse id(Long id) {
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
    TagResponse tagResponse = (TagResponse) o;
    return Objects.equals(this.nameKo, tagResponse.nameKo) &&
        Objects.equals(this.nameEn, tagResponse.nameEn) &&
        Objects.equals(this.description, tagResponse.description) &&
        Objects.equals(this.beers, tagResponse.beers) &&
        Objects.equals(this.id, tagResponse.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nameKo, nameEn, description, beers, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TagResponse {\n");
    sb.append("    nameKo: ").append(toIndentedString(nameKo)).append("\n");
    sb.append("    nameEn: ").append(toIndentedString(nameEn)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    beers: ").append(toIndentedString(beers)).append("\n");
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

