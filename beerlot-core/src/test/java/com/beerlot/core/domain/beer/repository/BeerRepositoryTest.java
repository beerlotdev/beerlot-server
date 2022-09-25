package com.beerlot.core.domain.beer.repository;

import com.beerlot.api.generated.model.FindBeerResDto;
import com.beerlot.core.common.BaseRepositoryTest;
import com.beerlot.core.config.QueryDslConfig;
import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.beer.Country;
import com.beerlot.core.domain.category.Category;
import com.beerlot.core.domain.common.page.PageCustomRequest;
import com.beerlot.core.domain.tag.BeerTag;
import com.beerlot.core.domain.tag.Tag;
import com.beerlot.core.domain.common.page.Page;
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
                .country(Country.US)
                .category(categoryAmericanBlondeAle)
                .imageUrl("https://beerlot.com/image_url")
                .build());

        beerMaineBeerDinner = save(Beer.builder()
                .nameKo("마이네 비어 디너")
                .nameEn("Maine Beer Dinner")
                .description("Maine Beer Dinner is blah blah.")
                .volume(8.2f)
                .country(Country.US)
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
            Page<FindBeerResDto> page = beerRepository.findBySearch(null, List.of(categoryImperialIPA.getId()), null, null, new PageCustomRequest(1, 2));
            assertEquals(1, page.getContents().size());
        }

        @Test
        public void givenTwoCategories() {
            Page<FindBeerResDto> page = beerRepository.findBySearch(null, List.of(categoryImperialIPA.getId(), categoryAmericanBlondeAle.getId()), null, null, new PageCustomRequest(1, 2));
            assertEquals(2, page.getContents().size());
        }

        @Test
        public void givenOneCountry() {
            Page<FindBeerResDto> page = beerRepository.findBySearch(null, null, List.of(Country.US), null, new PageCustomRequest(1, 2));
            assertEquals(2, page.getContents().size());
        }

        @Test
        public void givenCountryHasNoBeers() {
            Page<FindBeerResDto> page = beerRepository.findBySearch(null, null, List.of(Country.AD), null, new PageCustomRequest(1, 2));
            assertEquals(0, page.getContents().size());
        }

        @Test
        public void givenKeywordForBeerDescription() {
            Page<FindBeerResDto> page = beerRepository.findBySearch("Dinner", null, null, null, new PageCustomRequest(1, 2));
            assertEquals(1, page.getContents().size());
        }

        @Test
        public void givenKeywordForTagDescription() {
            Page<FindBeerResDto> page = beerRepository.findBySearch("Citra", null, null, null, new PageCustomRequest(1, 2));
            assertEquals(2, page.getContents().size());
        }

        @Test
        public void givenKeywordForCategoryDescription() {
            Page<FindBeerResDto> page = beerRepository.findBySearch("IPA", null, null, null, new PageCustomRequest(1, 2));
            assertEquals(1, page.getContents().size());
        }

        @Test
        public void givenOneVolume() {
            Page<FindBeerResDto> page = beerRepository.findBySearch(null, null, null, List.of(4), new PageCustomRequest(1, 2));
            assertEquals(1, page.getContents().size());
        }

        @Test
        public void givenTwoVolumes() {
            Page<FindBeerResDto> page = beerRepository.findBySearch(null, null, null, List.of(4, 8), new PageCustomRequest(1, 2));
            assertEquals(2, page.getContents().size());
        }

        @Test
        public void givenVolumeHasNoBeer() {
            Page<FindBeerResDto> page = beerRepository.findBySearch(null, null, null, List.of(5), new PageCustomRequest(1, 2));
            assertEquals(0, page.getContents().size());
        }

        @Test
        public void givenNoSearchCondition() {
            Page<FindBeerResDto> page = beerRepository.findBySearch(null, null, null, null, new PageCustomRequest(1, 2));
            assertEquals(2, page.getContents().size());
        }

        @Test
        public void pagination() {
            Page<FindBeerResDto> page = beerRepository.findBySearch(null, null, null, null, new PageCustomRequest(1, 1));
            assertEquals(1, page.getContents().size());
            assertEquals(2, page.getTotalPages());
            assertEquals(2, page.getTotalElements());
        }
    }
}

