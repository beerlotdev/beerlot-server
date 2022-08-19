package com.beerlot.core.beer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Country {
    BD("Bangladesh", "방글라데시"),
    KO("Korea", "대한민국"),
    US("The United States", "미국");

    private String nameEn;

    private String nameKo;
}
