package com.beerlot.core.domain.beer.repository;

import com.beerlot.core.common.BaseRepositoryTest;
import com.beerlot.core.config.QueryDslConfig;
import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.beer.Country;
import com.beerlot.core.domain.beer.dto.BeerResDto;
import com.beerlot.core.domain.category.Category;
import com.beerlot.core.domain.tag.BeerTag;
import com.beerlot.core.domain.tag.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(QueryDslConfig.class)
public class BeerRepositoryTest extends BaseRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    Beer beerBigwave;
    Beer beerMaineBeerDinner;
    Category categoryAmericanBlondeAle;
    Category categoryImperialIPA;
    Tag tagCitra;
    BeerTag beerTagBigwaveCitra;
    BeerTag beerTagMaineBeerDinnerCitra;

    @BeforeEach
    public void setUp() {
        categoryAmericanBlondeAle = save(Category.builder()
                .nameKo("아메리칸 블론드 에일")
                .nameEn("American Blonde Ale")
                .description("American Blonde Ale is blah blah.")
                .parent(null)
                .build());

        categoryImperialIPA = save(Category.builder()
                .nameKo("임페리얼 아이피에이")
                .nameEn("Imperial IPA")
                .description("Imperial IPA is bla bla.")
                .parent(null)
                .build());

        tagCitra = save(Tag.builder()
                .nameKo("시트라")
                .nameEn("Citra")
                .description("Citra is blah blah.")
                .build());

        beerBigwave = save(Beer.builder()
                .nameKo("빅 웨이브")
                .nameEn("Bigwave")
                .description("Bigwave is blah blah.")
                .volume(4.4f)
                .origin(Country.US)
                .category(categoryAmericanBlondeAle)
                .imageUrl("https://beerlot.com/image_url")
                .build());

        beerMaineBeerDinner = save(Beer.builder()
                .nameKo("마이네 비어 디너")
                .nameEn("Maine Beer Dinner")
                .description("Maine Beer Dinner is blah blah.")
                .volume(8.2f)
                .origin(Country.US)
                .category(categoryImperialIPA)
                .imageUrl("https://beerlot.com/image_url")
                .build());

        beerTagBigwaveCitra = merge(BeerTag.builder()
                .beer(beerBigwave)
                .tag(tagCitra)
                .build());

        beerTagMaineBeerDinnerCitra = merge(BeerTag.builder()
                .beer(beerMaineBeerDinner)
                .tag(tagCitra)
                .build());

    }

    @Nested
    class FindById {

        @Test
        public void findById() {
            Optional<Beer> beer = beerRepository.findById(beerBigwave.getId());
            Assertions.assertTrue(beer.isPresent());
        }
    }

    @Nested
    class FindBySearch {

        @Test
        public void givenOneCategory() {
            List<BeerResDto> beers = beerRepository.findBySearch(null, List.of(categoryImperialIPA), null, null);
            assertEquals(1, beers.size());
        }

        @Test
        public void givenTwoCategories() {
            List<BeerResDto> beers = beerRepository.findBySearch(null, List.of(categoryImperialIPA, categoryAmericanBlondeAle), null, null);
            assertEquals(2, beers.size());
        }

        @Test
        public void givenOneCountry() {
            List<BeerResDto> beers = beerRepository.findBySearch(null, null, List.of(Country.US), null);
            assertEquals(2, beers.size());
        }

        @Test
        public void givenCountryHasNoBeers() {
            List<BeerResDto> beers = beerRepository.findBySearch(null, null, List.of(Country.AD), null);
            assertEquals(0, beers.size());
        }

        @Test
        public void givenKeywordForBeerDescription() {
            List<BeerResDto> beers = beerRepository.findBySearch("Dinner", null, null, null);
            assertEquals(1, beers.size());
        }

        @Test
        public void givenKeywordForTagDescription() {
            List<BeerResDto> beers = beerRepository.findBySearch("Citra", null, null, null);
            assertEquals(2, beers.size());
        }

        @Test
        public void givenKeywordForCategoryDescription() {
            List<BeerResDto> beers = beerRepository.findBySearch("IPA", null, null, null);
            assertEquals(1, beers.size());
        }

        @Test
        public void givenOneVolume() {
            List<BeerResDto> beers = beerRepository.findBySearch(null, null, null, List.of(4));
            assertEquals(1, beers.size());
        }

        @Test
        public void givenTwoVolumes() {
            List<BeerResDto> beers = beerRepository.findBySearch(null, null, null, List.of(4, 8));
            assertEquals(2, beers.size());
        }

        @Test
        public void givenVolumeHasNoBeer() {
            List<BeerResDto> beers = beerRepository.findBySearch(null, null, null, List.of(5));
            assertEquals(0, beers.size());
        }

        @Test
        public void givenNoSearchCondition() {
            List<BeerResDto> beers = beerRepository.findBySearch(null, null, null, null);
            assertEquals(2, beers.size());
        }
    }
}

