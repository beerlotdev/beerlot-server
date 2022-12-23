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
 * Gets or Sets ProviderType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum ProviderType {
  
  GOOGLE("GOOGLE"),
  
  FACEBOOK("FACEBOOK"),
  
  KAKAO("KAKAO"),
  
  NAVER("NAVER");

  private String value;

  ProviderType(String value) {
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
  public static ProviderType fromValue(String value) {
    for (ProviderType b : ProviderType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

