package com.beerlot.domain.policy;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class PolicyTypeConverter implements AttributeConverter<Set<PolicyType>, String> {
    @Override
    public String convertToDatabaseColumn(Set<PolicyType> set) {
        return ObjectUtils.isEmpty(set) ? "" : set.stream().map(roleType -> roleType.toString()).collect(Collectors.joining(","));
    }

    @Override
    public Set<PolicyType> convertToEntityAttribute(String data) {
        return StringUtils.hasText(data) ?
                Arrays.stream(data.split(",")).map(str -> PolicyType.valueOf(str)).collect(Collectors.toSet()) :
                Collections.emptySet();
    }
}
