package com.example.restaurant.repository;

import com.example.restaurant.model.CityDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityDao, Long> {
    Optional<CityDao> findCityDaoByCityName(String cityName);
    boolean existsCityDaoByCityName(String cityName);

    boolean existsCityDaoByCityId(Long cityId);

    void deleteCityDaoByCityId(Long cityId);

    List<CityDao> findAll();
}
