package com.beerlot.domain.review;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class BuyFromConverter implements AttributeConverter<Set<String>, String> {
    @Override
    public String convertToDatabaseColumn(Set<String> set) {
        return ObjectUtils.isEmpty(set) ? "" : set.stream().collect(Collectors.joining(","));
    }

    @Override
    public Set<String> convertToEntityAttribute(String data) {
        return StringUtils.hasText(data) ?
                Arrays.stream(data.split(",")).collect(Collectors.toSet()) : Collections.emptySet();
    }
}
