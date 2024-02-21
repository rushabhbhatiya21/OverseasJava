package com.example.restaurant.repository;


import com.example.restaurant.model.AreaDao;
import com.example.restaurant.model.CityDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<AreaDao, Long> {
    Optional<AreaDao> findAreaDaoByAreaName(String areaName);

    Optional<AreaDao> findAreaDaoByAreaId(Long areaId);
    boolean existsAreaDaoByAreaName(String areaName);

    List<AreaDao> findAreaDaoByCity_CityId(Long city_cityId);

    void deleteAreaDaoByCity_CityId(Long cityId);

}
