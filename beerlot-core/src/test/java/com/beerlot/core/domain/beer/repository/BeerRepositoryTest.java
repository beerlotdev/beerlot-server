package com.beerlot.core.domain.beer.repository;

import com.beerlot.core.common.BaseRepositoryTest;
import com.beerlot.config.QueryDslConfig;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(QueryDslConfig.class)
public class BeerRepositoryTest extends BaseRepositoryTest {
    /*@Autowired
    BeerRepository beerRepository;

    Beer beerBigwave;
    Beer beerMaineBeerDinner;
    Category categoryAmericanBlondeAle;
    Category categoryImperialIPA;

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
            PageCustom<BeerResponse> pageCustom = beerRepository.findBySearch(null, List.of(categoryImperialIPA.getId()), null, null, new PageCustomRequest(1, 2, BeerSortType.MOST_LIKES));
            assertEquals(1, pageCustom.getContents().size());
        }

        @Test
        public void givenTwoCategories() {
            PageCustom<BeerResponse> pageCustom = beerRepository.findBySearch(null, List.of(categoryImperialIPA.getId(), categoryAmericanBlondeAle.getId()), null, null, new PageCustomRequest(1, 2, BeerSortType.MOST_LIKES));
            assertEquals(2, pageCustom.getContents().size());
        }

        @Test
        public void givenOneCountry() {
            PageCustom<BeerResponse> pageCustom = beerRepository.findBySearch(null, null, List.of(Country.US), null, new PageCustomRequest(1, 2, BeerSortType.MOST_LIKES));
            assertEquals(2, pageCustom.getContents().size());
        }

        @Test
        public void givenCountryHasNoBeers() {
            PageCustom<BeerResponse> pageCustom = beerRepository.findBySearch(null, null, List.of(Country.AD), null, new PageCustomRequest(1, 2, BeerSortType.MOST_LIKES));
            assertEquals(0, pageCustom.getContents().size());
        }

        @Test
        public void givenKeywordForBeerDescription() {
            PageCustom<BeerResponse> pageCustom = beerRepository.findBySearch("Dinner", null, null, null, new PageCustomRequest(1, 2, BeerSortType.MOST_LIKES));
            assertEquals(1, pageCustom.getContents().size());
        }

        @Test
        public void givenKeywordForTagDescription() {
            PageCustom<BeerResponse> pageCustom = beerRepository.findBySearch("Citra", null, null, null, new PageCustomRequest(1, 2, BeerSortType.MOST_LIKES));
            assertEquals(2, pageCustom.getContents().size());
        }

        @Test
        public void givenKeywordForCategoryDescription() {
            PageCustom<BeerResponse> pageCustom = beerRepository.findBySearch("IPA", null, null, null, new PageCustomRequest(1, 2, BeerSortType.MOST_LIKES));
            assertEquals(1, pageCustom.getContents().size());
        }

        @Test
        public void givenOneVolume() {
            PageCustom<BeerResponse> pageCustom = beerRepository.findBySearch(null, null, null, List.of(4), new PageCustomRequest(1, 2, BeerSortType.MOST_LIKES));
            assertEquals(1, pageCustom.getContents().size());
        }

        @Test
        public void givenTwoVolumes() {
            PageCustom<BeerResponse> pageCustom = beerRepository.findBySearch(null, null, null, List.of(4, 8), new PageCustomRequest(1, 2, BeerSortType.MOST_LIKES));
            assertEquals(2, pageCustom.getContents().size());
        }

        @Test
        public void givenVolumeHasNoBeer() {
            PageCustom<BeerResponse> pageCustom = beerRepository.findBySearch(null, null, null, List.of(5), new PageCustomRequest(1, 2, BeerSortType.MOST_LIKES));
            assertEquals(0, pageCustom.getContents().size());
        }

        @Test
        public void givenNoSearchCondition() {
            PageCustom<BeerResponse> pageCustom = beerRepository.findBySearch(null, null, null, null, new PageCustomRequest(1, 2, BeerSortType.MOST_LIKES));
            assertEquals(2, pageCustom.getContents().size());
        }

        @Test
        public void pagination() {
            PageCustom<BeerResponse> pageCustom = beerRepository.findBySearch(null, null, null, null, new PageCustomRequest(1, 1, BeerSortType.MOST_LIKES));
            assertEquals(1, pageCustom.getContents().size());
            assertEquals(2, pageCustom.getTotalPages());
            assertEquals(2, pageCustom.getTotalElements());
        }
    }*/
}

