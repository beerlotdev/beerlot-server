package com.beerlot.core.domain.tag;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_en", nullable = false, unique = true)
    private String nameEn;

    @Column(name = "name_ko", nullable = false, unique = true)
    private String nameKo;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Builder
    public Tag(String nameEn, String nameKo, String description) {
        this.nameEn = nameEn;
        this.nameKo = nameKo;
        this.description = description == null ? "" : description;
    }
}

