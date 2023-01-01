package com.beerlot.domain.beer.controller;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.beer.BeerInternational;
import com.beerlot.domain.beer.dto.response.BeerResponse;
import com.beerlot.domain.beer.service.BeerLikeService;
import com.beerlot.domain.beer.service.BeerService;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.exception.ConflictException;
import com.beerlot.tool.fixture.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    @MockBean
    BeerLikeService beerLikeService;

    Beer beer;
    BeerInternational beerInternational;

    @Nested
    class BeerTest {
        @Nested
        class FindByIdTest {

            @BeforeEach
            public void setUp() {
                beer = Fixture.createBeer();
                beerInternational = Fixture.createBeerInternational();
            }

            @Test
            public void success() throws Exception {
                Mockito.when(beerService.findBeerByIdAndLanguage(1L, LanguageType.KR))
                        .thenReturn(BeerResponse.of(beer, beerInternational));

                mockMvc.perform(get("/api/v1/beers/{beerId}", 1)
                                .param("language", String.valueOf(LanguageType.KR))
                        )
                        .andExpect(status().isOk());
            }

            @Test
            public void beerNotExist() throws Exception {
                Mockito.when(beerService.findBeerByIdAndLanguage(2L, LanguageType.KR))
                        .thenThrow(NoSuchElementException.class);

                mockMvc.perform(get("/api/v1/beers/{beerId}", 2)
                                .param("language", String.valueOf(LanguageType.KR))
                        )
                        .andExpect(status().isNotFound());
            }
        }

        @Nested
        class FindBySearchTest {

        }

        @Nested
        class FindTop10Test {

        }
    }


    @Nested
    class BeerLikeTest {

        @Nested
        class CreateBeerLikeTest {

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void success() throws Exception {
                doNothing().when(beerLikeService).likeBeer(isA(Long.class));

                mockMvc.perform(post("/api/v1/beers/{beerId}/likes", 1)
                        )
                        .andExpect(status().isCreated());
            }

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void beerNotExist() throws Exception {
                doThrow(NoSuchElementException.class).when(beerLikeService).likeBeer(2L);

                mockMvc.perform(post("/api/v1/beers/{beerId}/likes", 2)
                        )
                        .andExpect(status().isNotFound());
            }

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void beerLikeAlreadyExist() throws Exception {
                doThrow(ConflictException.class).when(beerLikeService).likeBeer(1L);

                mockMvc.perform(post("/api/v1/beers/{beerId}/likes", 1)
                        )
                        .andExpect(status().isConflict());
            }
        }

        @Nested
        class DeleteBeerLikeTest {

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void success() throws Exception {
                doNothing().when(beerLikeService).unlikeBeer(1L);

                mockMvc.perform(delete("/api/v1/beers/{beerId}/likes", 1)
                        )
                        .andExpect(status().isNoContent());
            }

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void beerOrBeerLikeNotExist() throws Exception {
                doThrow(NoSuchElementException.class).when(beerLikeService).unlikeBeer(2L);

                mockMvc.perform(delete("/api/v1/beers/{beerId}/likes", 2)
                        )
                        .andExpect(status().isNotFound());
            }
        }
    }
}
