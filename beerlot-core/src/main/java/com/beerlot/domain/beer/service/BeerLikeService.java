package com.beerlot.domain.beer.service;

import com.beerlot.domain.beer.Beer;
import com.beerlot.domain.beer.BeerLike;
import com.beerlot.domain.beer.repository.BeerLikeRepository;
import com.beerlot.domain.beer.repository.BeerRepository;
import com.beerlot.domain.member.repository.MemberRepository;
import com.beerlot.exception.ConflictException;
import com.beerlot.exception.ErrorMessage;
import com.beerlot.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class BeerLikeService {

    @Autowired
    private BeerLikeRepository beerLikeRepository;

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private BeerService beerService;

    @Autowired
    private MemberRepository memberRepository;

    /* TODO: Include memberId and validateMember*/
    public void likeBeer(Long beerId) {
        Beer beer = beerService.findBeerById(beerId);
        checkBeerLikeExist(beerId, 1L, true);
        BeerLike beerLike = new BeerLike(beer, memberRepository.findById(1L).get());
        beerLikeRepository.save(beerLike);
    }

    /* TODO: Include memberId and validateMember*/
    public void unlikeBeer(Long beerId) {
        Beer beer = beerService.findBeerById(beerId);
        checkBeerLikeExist(beerId, 1L, false);
        beerLikeRepository.deleteByBeer_IdAndMember_Id(beerId, 1L);
        beer.unlikeBeer();
    }

    private void checkBeerExist(Long beerId) {
        if (!beerRepository.existsById(beerId)) {
            throw new NoSuchElementException(ErrorMessage.BEER__NOT_EXIST.getMessage());
        }
    }

    private void checkBeerLikeExist(Long beerId, Long memberId, boolean isPositive) {
        if (isPositive && beerLikeRepository.existsByBeer_IdAndMember_Id(beerId, memberId)) {
            throw new ConflictException(ErrorMessage.BEER_LIKE_CONFLICT);
        } else if (!isPositive && !beerLikeRepository.existsByBeer_IdAndMember_Id(beerId, memberId)) {
            throw new NoSuchElementException(ErrorMessage.BEER_LIKE_NOT_FOUND.getMessage());
        }
    }
}
