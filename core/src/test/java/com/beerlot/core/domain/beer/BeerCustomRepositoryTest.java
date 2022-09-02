package com.beerlot.core.domain.beer;

import com.beerlot.core.config.QueryDslConfig;
import com.beerlot.core.domain.tag.Tag;
import com.beerlot.core.domain.tag.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Import(QueryDslConfig.class)
@SpringBootTest
public class BeerCustomRepositoryTest {
    @Autowired
    BeerCustomRepository beerCustomRepository;
    @Autowired
    BeerRepository beerRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    BeerTagRepository beerTagRepository;
    @Autowired
    CategoryRepository categoryRepository;

    Beer beer1;
    Category ctAmericanBlondeAle;
    Tag tgGinger;
    BeerTag beerTag1;

    @BeforeEach
    public void setUp() {
        ctAmericanBlondeAle = Category.builder()
                .nameKo("아메리칸 블론드 에일")
                .nameEn("American Blonde Ale")
                .description("This is American Blonde Ale")
                .parent(null)
                .build();

        categoryRepository.save(ctAmericanBlondeAle);

        tgGinger = Tag.builder()
                .nameKo("생강")
                .nameEn("Ginger")
                .description("This is Ginger.")
                .build();

        tagRepository.save(tgGinger);

        beer1 = Beer.builder()
                .nameKo("빅 웨이브")
                .nameEn("Big Wave")
                .description("This is Bigwave.")
                .volume(4.4f)
                .origin(Country.US)
                .category(ctAmericanBlondeAle)
                .build();

        beerRepository.save(beer1);

        beerTag1 = BeerTag.builder()
                .beer(beer1)
                .tag(tgGinger)
                .build();

        beerTagRepository.save(beerTag1);

    }

    @Test
    public void findById() {
        Optional<Beer> beer = beerRepository.findById(1L);
        Assertions.assertEquals(1L, beer.get().getId());
    }

    @Test
    public void findByKeywordAndTags() {
        List<Beer> beers = beerCustomRepository.findByKeywordAndTags(null, List.of(tgGinger));
        Assertions.assertEquals(1, beers.size());
    }
}
