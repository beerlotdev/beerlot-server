package com.beerlot.api.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets BeerSortType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum BeerSortType {
  
  MOST_LIKES("MOST_LIKES"),
  
  MOST_REVIEWS("MOST_REVIEWS"),
  
  HIGH_RATE("HIGH_RATE");

  private String value;

  BeerSortType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BeerSortType fromValue(String value) {
    for (BeerSortType b : BeerSortType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

