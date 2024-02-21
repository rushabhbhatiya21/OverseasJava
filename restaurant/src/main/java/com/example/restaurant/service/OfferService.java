package com.example.restaurant.service;

import com.example.restaurant.model.OfferDao;
import com.example.restaurant.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Page<OfferDao> findOffersForRestaurant(Long userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return offerRepository.findAllByRestaurant_UserId(userId, pageable);
    }

    public Page<OfferDao> findAllOffers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return offerRepository.findAll(pageable);
    }

    public boolean existsOfferByName(String offerName) {
        return offerRepository.existsOfferDaoByOfferName(offerName);
    }

    public void saveOffer(OfferDao offer) {
        offerRepository.save(offer);
    }

    public void deleteOfferById(Long offerId) {
        Optional<OfferDao> offer = offerRepository.findById(offerId);
        try {
            offer.get().setRestaurant(null);
            offer.get().setSubCategory(null);
            offerRepository.deleteById(offerId);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//    public List<OfferDao> findAllOffers(Long userId) {
//        return offerRepository.findAllByRestaurant_UserId(userId);
//    }
}
