package com.beerlot.domain.review.controller;

import com.beerlot.domain.common.page.PageCustomRequest;
import com.beerlot.domain.review.Review;
import com.beerlot.domain.review.ReviewSortType;
import com.beerlot.domain.review.dto.request.ReviewRequest;
import com.beerlot.domain.review.dto.response.ReviewPage;
import com.beerlot.domain.review.dto.response.ReviewResponse;
import com.beerlot.domain.review.service.ReviewLikeService;
import com.beerlot.domain.review.service.ReviewService;
import com.beerlot.exception.ConflictException;
import com.beerlot.tool.fixture.Fixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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

    @BeforeEach
    public void setUp() {
        review = Fixture.createReview();
    }

    @Nested
    class ReviewTest {

        @BeforeEach
        public void setUp() {
            reviewRequest = ReviewRequest.builder()
                    .content("이 맥주 최고!")
                    .rate(5.0f)
                    .imageUrl("https://beerlot.com/image_url")
                    .build();
        }

        @Nested
        class CreateReviewTest {

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void success() throws Exception {

                doNothing().when(reviewService).createReview(1L, reviewRequest);

                mockMvc.perform(post("/api/v1/beers/{beerId}/reviews", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reviewRequest))
                                .with(csrf())
                        )
                        .andExpect(status().isCreated());
            }

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void beerNotExist() throws Exception {
                doThrow(NoSuchElementException.class).when(reviewService).createReview(isA(Long.class), isA(ReviewRequest.class));

                mockMvc.perform(post("/api/v1/beers/{beerId}/reviews", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reviewRequest))
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound());
            }
        }

        @Nested
        class UpdateReviewTest {

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void success() throws Exception {

                when(reviewService.updateReview(isA(Long.class), isA(ReviewRequest.class)))
                        .thenReturn(ReviewResponse.of(review));

                mockMvc.perform(patch("/api/v1/reviews/{reviewId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reviewRequest))
                                .with(csrf())
                        )
                        .andExpect(status().isOk());
            }

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void reviewNotExist() throws Exception {
                when(reviewService.updateReview(isA(Long.class), isA(ReviewRequest.class)))
                        .thenThrow(NoSuchElementException.class);

                mockMvc.perform(patch("/api/v1/reviews/{reviewId}", 2)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reviewRequest))
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound());
            }
        }

        @Nested
        class DeleteReviewTest {

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void success() throws Exception {

                doNothing().when(reviewService).deleteReview(isA(Long.class));

                mockMvc.perform(delete("/api/v1/reviews/{reviewId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                        )
                        .andExpect(status().isNoContent());
            }

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void reviewNotExist() throws Exception {
                doThrow(NoSuchElementException.class).when(reviewService).deleteReview(isA(Long.class));

                mockMvc.perform(delete("/api/v1/reviews/{reviewId}", 2)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound());
            }
        }

        @Nested
        class FindReviewByIdTest {
            @Test
            @WithMockUser
            public void success() throws Exception {

                when(reviewService.findReviewById(isA(Long.class)))
                        .thenReturn(ReviewResponse.of(review));

                mockMvc.perform(get("/api/v1/reviews/{reviewId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                        )
                        .andExpect(status().isOk());
            }

            @Test
            @WithMockUser
            public void reviewNotExist() throws Exception {
                doThrow(NoSuchElementException.class).when(reviewService).findReviewById(isA(Long.class));

                mockMvc.perform(get("/api/v1/reviews/{reviewId}", 2)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound());
            }
        }

        @Nested
        class FindReviewsByBeerIdTest {
            @Test
            @WithMockUser
            public void success() throws Exception {

                when(reviewService.findByBeerId(isA(Long.class), isA(Integer.class), isA(Integer.class), isA(ReviewSortType.class)))
                        .thenReturn(new ReviewPage(
                                List.of(ReviewResponse.of(review)),
                                new PageCustomRequest(1, 10, ReviewSortType.MOST_LIKES),
                                1L));

                mockMvc.perform(get("/api/v1/beers/{beerId}/reviews", 1)
                                .param("page", "1")
                                .param("size", "10")
                                .param("sort", "MOST_LIKES")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                        )
                        .andExpect(status().isOk());
            }

            @Test
            @WithMockUser
            public void beerNotExist() throws Exception {
                when(reviewService.findByBeerId(isA(Long.class), isA(Integer.class), isA(Integer.class), isA(ReviewSortType.class)))
                        .thenThrow(NoSuchElementException.class);

                mockMvc.perform(get("/api/v1/beers/{beerId}/reviews", 2)
                                .param("page", "1")
                                .param("size", "10")
                                .param("sort", "MOST_LIKES")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound());
            }
        }

        @Nested
        class FindAllReviewsTest {

            @Test
            @WithMockUser
            public void success() throws Exception {
                when(reviewService.findAllReviews(isA(Integer.class), isA(Integer.class), isA(ReviewSortType.class)))
                        .thenReturn(new ReviewPage(
                                List.of(ReviewResponse.of(review)),
                                new PageCustomRequest(1, 10, ReviewSortType.MOST_LIKES),
                                1L));

                mockMvc.perform(get("/api/v1//reviews")
                                .param("page", "1")
                                .param("size", "10")
                                .param("sort", "MOST_LIKES")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                        )
                        .andExpect(status().isOk());
            }
        }
    }

    @Nested
    class ReviewLikeTest {

        @BeforeEach
        public void setUp() {
            review = Fixture.createReview();
        }

        @Nested
        class CreateReviewLikeTest {

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void success() throws Exception {
                doNothing().when(reviewLikeService).likeReview(isA(Long.class));
                //when(reviewService.findById(isA(Long.class))).thenReturn(review);

                mockMvc.perform(post("/api/v1/reviews/1/likes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                        )
                        .andExpect(status().isCreated());
            }

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void reviewNotExist() throws Exception {
                doThrow(NoSuchElementException.class).when(reviewLikeService).likeReview(isA(Long.class));

                mockMvc.perform(post("/api/v1/reviews/{reviewId}/likes", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound());
            }

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void reviewLikeAlreadyExist() throws Exception {
                doThrow(ConflictException.class).when(reviewLikeService).likeReview(isA(Long.class));

                mockMvc.perform(post("/api/v1/reviews/{reviewId}/likes", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                        )
                        .andExpect(status().isConflict());
            }
        }

        @Nested
        class DeleteReviewLikeTest {

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void success() throws Exception {
                doNothing().when(reviewLikeService).unlikeReview(isA(Long.class));

                mockMvc.perform(delete("/api/v1/reviews/{reviewId}/likes", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                        )
                        .andExpect(status().isNoContent());
            }

            @Test
            @WithMockUser(roles = {"MEMBER"})
            public void reviewOrReviewLikeNotExist() throws Exception {
                doThrow(NoSuchElementException.class).when(reviewLikeService).unlikeReview(isA(Long.class));

                mockMvc.perform(delete("/api/v1/reviews/{reviewId}/likes", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound());
            }
        }
    }
}
