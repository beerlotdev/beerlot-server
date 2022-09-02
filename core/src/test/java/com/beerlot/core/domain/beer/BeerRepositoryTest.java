package com.beerlot.core.domain.beer;

import com.beerlot.core.BaseRepositoryTest;
import com.beerlot.core.config.QueryDslConfig;
import com.beerlot.core.domain.tag.Tag;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

@Import(QueryDslConfig.class)
public class BeerRepositoryTest extends BaseRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    Beer beer;
    Category ctAmericanBlondeAle;
    Tag tgGinger;
    BeerTag beerTag;

    @BeforeEach
    public void setUp() {
        ctAmericanBlondeAle = save(Category.builder()
                .nameKo("아메리칸 블론드 에일")
                .nameEn("American Blonde Ale")
                .description("This is American Blonde Ale")
                .parent(null)
                .build());

        tgGinger = save(Tag.builder()
                .nameKo("생강")
                .nameEn("Ginger")
                .description("This is Ginger.")
                .build());

        beer = save(Beer.builder()
                .nameKo("빅 웨이브")
                .nameEn("Big Wave")
                .description("This is Bigwave.")
                .volume(4.4f)
                .origin(Country.US)
                .category(ctAmericanBlondeAle)
                .build());

        beerTag = merge(BeerTag.builder()
                .beer(beer)
                .tag(tgGinger)
                .build());
    }

    @Test
    public void findById() {
        Optional<Beer> beer = beerRepository.findById(1L);
        Assertions.assertEquals(1L, beer.get().getId());
    }

    @Test
    public void findByKeywordAndTags() {
        List<Beer> beers = beerRepository.findByKeywordAndTags(null, List.of(tgGinger));
        Assertions.assertEquals(1, beers.size());
    }
}
