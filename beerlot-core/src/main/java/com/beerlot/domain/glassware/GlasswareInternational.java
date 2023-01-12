package com.beerlot.domain.glassware;

import com.beerlot.domain.common.entity.LanguageType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "glassware_international")
public class GlasswareInternational {

    @EmbeddedId
    private GlasswareInternationalId id;

    @MapsId("glasswareId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "glassware_id")
    private Glassware glassware;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Builder
    public GlasswareInternational(Glassware glassware, LanguageType language, String name, String description) {
        this.id = new GlasswareInternationalId(glassware.getId(), language);
        this.name = name;
        this.description = description;
    }
}
