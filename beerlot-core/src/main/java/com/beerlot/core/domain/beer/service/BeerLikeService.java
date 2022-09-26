package com.beerlot.core.domain.beer.service;

import com.beerlot.core.domain.beer.BeerLike;
import com.beerlot.core.domain.beer.repository.BeerLikeRepository;
import com.beerlot.core.domain.beer.repository.BeerRepository;
import com.beerlot.core.domain.member.repository.MemberRepository;
import com.beerlot.core.exception.ConflictException;
import com.beerlot.core.exception.ErrorCode;
import com.beerlot.core.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BeerLikeService {

    @Autowired
    private BeerLikeRepository beerLikeRepository;

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private MemberRepository memberRepository;

    /* TODO: Include memberId and validateMember*/
    public void likeBeer(Long beerId) {
        validateBeer(beerId);
        validateBeerLike(beerId, 1L);
        BeerLike beerLike = new BeerLike(beerRepository.findById(beerId).get(), memberRepository.findById(1L).get());
        beerLikeRepository.save(beerLike);
    }

    public void unlikeBeer(Long beerId) {
        validateBeer(beerId);
        if (!beerLikeRepository.existsByBeer_IdAndMember_Id(beerId, 1L)) {
            throw new NotFoundException(ErrorCode.BEER_LIKE_NOT_FOUND);
        }
        beerLikeRepository.deleteByBeer_IdAndMember_Id(beerId, 1L);
        beerRepository.findById(beerId).get().unlikeBeer();
    }

    private void validateBeer(Long beerId) {
        if (!beerRepository.existsById(beerId)) {
            throw new NotFoundException(ErrorCode.BEER_NOT_FOUND);
        }
    }

    private void validateBeerLike(Long beerId, Long memberId) {
        if (beerLikeRepository.existsByBeer_IdAndMember_Id(beerId, memberId)) {
            throw new ConflictException(ErrorCode.BEER_LIKE_CONFLICT);
        }
    }
}
