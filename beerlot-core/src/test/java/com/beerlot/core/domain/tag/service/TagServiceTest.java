/*package com.beerlot.core.domain.tag.service;

import com.beerlot.api.generated.model.TagResponse;
import com.beerlot.core.common.BaseServiceTest;
import com.beerlot.core.domain.beer.Beer;
import com.beerlot.core.domain.category.Category;
import com.beerlot.core.domain.tag.BeerTag;
import com.beerlot.core.domain.tag.Tag;
import com.beerlot.core.domain.tag.repository.BeerTagRepository;
import com.beerlot.core.domain.tag.repository.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest extends BaseServiceTest {

    @Mock
    TagRepository tagRepository;

    @Mock
    BeerTagRepository beerTagRepository;

    @InjectMocks
    TagService tagService;


    Category category = createCategory();
    Beer beer = createBeer(1L, "블루문 벨지엄 위트", "Bluemoon", "This is Bluemoon.", "https://image.com", 4.5f, Country.US, category);
    Tag tag = createTag(1L, "시트라", "Citra", "This is Citra hop.");
    BeerTag beerTag = createBeerTag(beer, tag);

    @BeforeEach
    private void setUp() {

    }

    @Nested
    class FindByTagId {
        @Test
        public void findByTagId() {
            when(tagRepository.findById(isA(Long.class))).thenReturn(Optional.of(tag));
            when(beerTagRepository.findByTag_Id(isA(Long.class))).thenReturn(List.of(beerTag));

            TagResponse tagResponse = tagService.findTagById(1L);

            Assertions.assertEquals(tag.getId(), tagResponse.getId());
            Assertions.assertEquals(1, tagResponse.getBeers().size());
        }
    }

    public Tag createTag(Long id, String nameKo, String nameEn, String description) {
        Tag tag = Tag.builder()
                .nameKo(nameKo)
                .nameEn(nameEn)
                .description(description)
                .build();
        return tag;
    }

    public Category createCategory() {
        Category category = Category.builder()
                .parent(null)
                .build();
        return category;
    }

    public Beer createBeer(Long id, String imageUrl, Float volume, Country country, Category category) {
        Beer beer = Beer.builder()
                .id(id)
                .imageUrl(imageUrl)
                .volume(volume)
                .category(category)
                .build();
        return beer;
    }

    public BeerTag createBeerTag(Beer beer, Tag tag) {
        BeerTag beerTag = BeerTag.builder()
                .beer(beer)
                .tag(tag)
                .build();
        return beerTag;
    }
}
*/