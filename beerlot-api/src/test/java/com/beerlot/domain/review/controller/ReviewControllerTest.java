package com.beerlot.domain.review.controller;

import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.common.page.PageCustomImpl;
import com.beerlot.domain.common.page.PageCustomRequest;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.review.Review;
import com.beerlot.domain.review.ReviewSortType;
import com.beerlot.domain.review.dto.request.ReviewRequest;
import com.beerlot.domain.review.dto.response.ReviewResponse;
import com.beerlot.domain.review.service.ReviewLikeService;
import com.beerlot.domain.review.service.ReviewService;
import com.beerlot.exception.ConflictException;
import com.beerlot.tool.fixture.BeerFixture;
import com.beerlot.tool.fixture.MemberFixture;
import com.beerlot.tool.fixture.ReviewFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ReviewService reviewService;

    @MockBean
    ReviewLikeService reviewLikeService;

    ReviewRequest reviewRequest;
    Review review;

    @Nested
    class ReviewTest {

        @BeforeEach
        public void setUp() {
            review = ReviewFixture.createReview(BeerFixture.createBeer());
            reviewRequest = ReviewRequest.builder()
                    .content("이 맥주 최고!")
                    .rate(5.0f)
                    .imageUrl("https://beerlot.com/image_url")
                    .build();
        }

        @Nested
        class CreateReviewTest {

            Member member;

            @BeforeEach
            public void setUp() {
                member = MemberFixture.createMember();
            }

            @Test
            public void success() throws Exception {

                doNothing().when(reviewService).createReview(isA(String.class), isA(Long.class), isA(ReviewRequest.class));

                mockMvc.perform(post("/api/v1/beers/{beerId}/reviews", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reviewRequest))
                                .with(user(OAuthUserPrincipal.of(member)))
                        )
                        .andExpect(status().isCreated());
            }

            @Test
            public void beerNotExist() throws Exception {
                doThrow(NoSuchElementException.class).when(reviewService).createReview(isA(String.class), isA(Long.class), isA(ReviewRequest.class));

                mockMvc.perform(post("/api/v1/beers/{beerId}/reviews", 100)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reviewRequest))
                                .with(user(OAuthUserPrincipal.of(member)))
                        )
                        .andExpect(status().isNotFound());
            }

            @Test
            public void reviewAlreadyExist() throws Exception {
                doThrow(ConflictException.class).when(reviewService).createReview(isA(String.class), isA(Long.class), isA(ReviewRequest.class));

                mockMvc.perform(post("/api/v1/beers/{beerId}/reviews", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reviewRequest))
                                .with(user(OAuthUserPrincipal.of(member)))
                        )
                        .andExpect(status().isConflict());
            }
        }

        @Nested
        class UpdateReviewTest {

            Member member;

            @BeforeEach
            public void setUp() {
                member = MemberFixture.createMember();
            }

            @Test
            public void success() throws Exception {

                when(reviewService.updateReview(isA(String.class), isA(Long.class), isA(ReviewRequest.class)))
                        .thenReturn(ReviewResponse.of(review));

                mockMvc.perform(patch("/api/v1/reviews/{reviewId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reviewRequest))
                                .with(user(OAuthUserPrincipal.of(member)))
                        )
                        .andExpect(status().isOk());
            }

            @Test
            public void reviewNotExist() throws Exception {
                when(reviewService.updateReview(isA(String.class), isA(Long.class), isA(ReviewRequest.class)))
                        .thenThrow(NoSuchElementException.class);

                mockMvc.perform(patch("/api/v1/reviews/{reviewId}", 2)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reviewRequest))
                                .with(user(OAuthUserPrincipal.of(member)))
                        )
                        .andExpect(status().isNotFound());
            }
        }

        @Nested
        class DeleteReviewTest {

            Member member;

            @BeforeEach
            public void setUp() {
                member = MemberFixture.createMember();
            }

            @Test
            public void success() throws Exception {

                doNothing().when(reviewService).deleteReview(isA(String.class), isA(Long.class));

                mockMvc.perform(delete("/api/v1/reviews/{reviewId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(user(OAuthUserPrincipal.of(member)))
                        )
                        .andExpect(status().isNoContent());
            }

            @Test
            public void reviewNotExist() throws Exception {
                doThrow(NoSuchElementException.class).when(reviewService).deleteReview(isA(String.class), isA(Long.class));

                mockMvc.perform(delete("/api/v1/reviews/{reviewId}", 2)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(user(OAuthUserPrincipal.of(member)))
                        )
                        .andExpect(status().isNotFound());
            }
        }

        @Nested
        class FindReviewByIdTest {

            Member member;

            @BeforeEach
            public void setUp() {
                member = MemberFixture.createMember();
            }

            @Test
            @WithAnonymousUser
            public void success() throws Exception {

                when(reviewService.findByReviewIdAndLanguage(isA(Long.class), isA(LanguageType.class)))
                        .thenReturn(ReviewResponse.of(review));

                mockMvc.perform(get("/api/v1/reviews/{reviewId}", 1)
                                .param("language", "KR")
                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk());
            }

            @Test
            @WithAnonymousUser
            public void reviewNotExist() throws Exception {
                doThrow(NoSuchElementException.class).when(reviewService).findByReviewIdAndLanguage(isA(Long.class), isA(LanguageType.class));

                mockMvc.perform(get("/api/v1/reviews/{reviewId}", 2)
                                .param("language", "KR")
                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isNotFound());
            }
        }

        @Nested
        class FindReviewsByBeerIdTest {
            @Test
            @WithAnonymousUser
            public void success() throws Exception {

                when(reviewService.findByBeerId(isA(Long.class), isA(PageCustomRequest.class)))
                        .thenReturn(new PageCustomImpl<>(
                                List.of(ReviewResponse.of(review)),
                                new PageCustomRequest(1, 10, ReviewSortType.MOST_LIKES),
                                1L));

                mockMvc.perform(get("/api/v1/beers/{beerId}/reviews", 1)
                                .param("page", "1")
                                .param("size", "10")
                                .param("sort", "MOST_LIKES")
                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk());
            }

            @Test
            @WithAnonymousUser
            public void beerNotExist() throws Exception {
                when(reviewService.findByBeerId(isA(Long.class), isA(PageCustomRequest.class)))
                        .thenThrow(NoSuchElementException.class);

                mockMvc.perform(get("/api/v1/beers/{beerId}/reviews", 2)
                                .param("page", "1")
                                .param("size", "10")
                                .param("sort", "MOST_LIKES")
                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isNotFound());
            }
        }

        @Nested
        class FindAllReviewsTest {

            @Test
            @WithAnonymousUser
            public void success() throws Exception {
                when(reviewService.findAllReviews(isA(PageCustomRequest.class), isA(LanguageType.class)))
                        .thenReturn(new PageCustomImpl<>(
                                List.of(ReviewResponse.of(review)),
                                new PageCustomRequest(1, 10, ReviewSortType.MOST_LIKES),
                                1L));

                mockMvc.perform(get("/api/v1//reviews")
                                .param("page", "1")
                                .param("size", "10")
                                .param("sort", "MOST_LIKES")
                                .param("language", "KR")
                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk());
            }
        }
    }

    @Nested
    class ReviewLikeTest {

        @BeforeEach
        public void setUp() {
            review = ReviewFixture.createReview(BeerFixture.createBeer());
        }

        @Nested
        class CreateReviewLikeTest {

            Member member;

            @BeforeEach
            public void setUp() {
                member = MemberFixture.createMember();
            }

            @Test
            public void success() throws Exception {
                doNothing().when(reviewLikeService).likeReview(isA(String.class), isA(Long.class));

                mockMvc.perform(post("/api/v1/reviews/1/likes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(user(OAuthUserPrincipal.of(member)))
                        )
                        .andExpect(status().isCreated());
            }

            @Test
            public void reviewNotExist() throws Exception {
                doThrow(NoSuchElementException.class).when(reviewLikeService).likeReview(isA(String.class), isA(Long.class));

                mockMvc.perform(post("/api/v1/reviews/{reviewId}/likes", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(user(OAuthUserPrincipal.of(member)))
                        )
                        .andExpect(status().isNotFound());
            }

            @Test
            public void reviewLikeAlreadyExist() throws Exception {
                doThrow(ConflictException.class).when(reviewLikeService).likeReview(isA(String.class), isA(Long.class));

                mockMvc.perform(post("/api/v1/reviews/{reviewId}/likes", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(user(OAuthUserPrincipal.of(member)))
                        )
                        .andExpect(status().isConflict());
            }
        }

        @Nested
        class DeleteReviewLikeTest {

            Member member;

            @BeforeEach
            public void setUp() {
                member = MemberFixture.createMember();
            }

            @Test
            public void success() throws Exception {
                doNothing().when(reviewLikeService).unlikeReview(isA(String.class), isA(Long.class));

                mockMvc.perform(delete("/api/v1/reviews/{reviewId}/likes", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(user(OAuthUserPrincipal.of(member)))
                        )
                        .andExpect(status().isNoContent());
            }

            @Test
            public void reviewOrReviewLikeNotExist() throws Exception {
                doThrow(NoSuchElementException.class).when(reviewLikeService).unlikeReview(isA(String.class), isA(Long.class));

                mockMvc.perform(delete("/api/v1/reviews/{reviewId}/likes", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(user(OAuthUserPrincipal.of(member)))
                        )
                        .andExpect(status().isNotFound());
            }
        }
    }
}
