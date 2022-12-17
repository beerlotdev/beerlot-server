package com.beerlot.core.domain.member;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Convert
public class RoleTypeConverter implements AttributeConverter<Set<RoleType>, String> {
    @Override
    public String convertToDatabaseColumn(Set<RoleType> set) {
        return ObjectUtils.isEmpty(set) ? "" : set.stream().map(roleType -> roleType.toString()).collect(Collectors.joining(","));
    }

    @Override
    public Set<RoleType> convertToEntityAttribute(String data) {
        return StringUtils.hasText(data) ?
                Arrays.stream(data.split(",")).map(s -> RoleType.valueOf(s)).collect(Collectors.toSet()) :
                Collections.emptySet();
    }
}
