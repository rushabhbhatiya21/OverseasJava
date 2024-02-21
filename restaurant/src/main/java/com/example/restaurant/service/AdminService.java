package com.example.restaurant.service;

import com.example.restaurant.model.AreaDao;
import com.example.restaurant.model.CityDao;
import com.example.restaurant.model.UserDao;
import com.example.restaurant.repository.AreaRepository;
import com.example.restaurant.repository.CityRepository;
import com.example.restaurant.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final AreaRepository areaRepository;
    private final CityRepository cityRepository;

    @Autowired
    public AdminService(UserRepository userRepository, AreaRepository areaRepository, CityRepository cityRepository) {
        this.userRepository = userRepository;
        this.areaRepository = areaRepository;
        this.cityRepository = cityRepository;
    }

    // ------------------------ USER ------------------------- //

    public Page<UserDao> findAllUserPages(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return userRepository.findAllByRole(UserDao.Role.USER, pageable);
    }

    public List<UserDao> findAllRestaurants() {
        return userRepository.findUserDaoByRole(UserDao.Role.USER);
    }

    public Optional<UserDao> findUserByRestaurantName(String restaurantName) {
        return userRepository.findUserDaoByRestaurantName(restaurantName);
    }

    public Optional<UserDao> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void saveUser(UserDao user) {
        userRepository.save(user);
    }

    public Optional<UserDao> findUserByEmail(String email) {
        return userRepository.findUserDaoByEmail(email);
    }

    public boolean userExists(String email) {
        Optional<UserDao> user = userRepository.findUserDaoByEmail(email);
        return user.isPresent();
    }

    public void deleteRestaurantById(Long userId) {
        Optional<UserDao> user = userRepository.findById(userId);

        if (user.isPresent()) {
            user.get().setArea(null);
            user.get().setCity(null);
        }

        userRepository.deleteById(userId);
    }

    // ------------------------ CITY ------------------------- //
    public Optional<CityDao> findCityByCityName(String cityName) {
        return cityRepository.findCityDaoByCityName(cityName);
    }
    public void saveCity(CityDao city) { cityRepository.save(city); }

    public boolean existsCityByName(String cityName) {
        return cityRepository.existsCityDaoByCityName(cityName);
    }

    public Optional<CityDao> findCityByName(String cityName) {
        return cityRepository.findCityDaoByCityName(cityName);
    }

    public Page<CityDao> findAllCityPages(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return cityRepository.findAll(pageable);
    }

    public List<CityDao> findAllCities() {
        return cityRepository.findAll();
    }

    public List<AreaDao> findAllAreas() {
        return areaRepository.findAll();
    }

    @Transactional
    public void deleteCityById(Long cityId) {
        try {
            areaRepository.deleteAreaDaoByCity_CityId(cityId);
            cityRepository.deleteById(cityId);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // ------------------------ AREA ------------------------- //
    public void saveArea(AreaDao area) {
        areaRepository.save(area);
    }

    public Optional<AreaDao> findAreaByAreaName(String areaName) {
        return areaRepository.findAreaDaoByAreaName(areaName);
    }

    public boolean existsAreaByName(String areaName) {
        return areaRepository.existsAreaDaoByAreaName(areaName);
    }

    public Optional<AreaDao> findAreaByName(String areaName) {
        return areaRepository.findAreaDaoByAreaName(areaName);
    }

    public Page<AreaDao> findAllAreaPages(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return areaRepository.findAll(pageable);
    }

    public void deleteAreaById(Long areaId) {
        System.out.println("here");
        Optional<AreaDao> area = areaRepository.findAreaDaoByAreaId(areaId);
        area.get().setCity(null);
        areaRepository.deleteById(area.get().getAreaId());
    }

    public List<AreaDao> findAreaByCityId(Long cityId) {
        return areaRepository.findAreaDaoByCity_CityId(cityId);
    }


    public void addAttributesToModel(Model model, String pageNo, Page page) {
        model.addAttribute("currentPage", Integer.parseInt(pageNo));
        model.addAttribute("no", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("size", page.getSize());
        model.addAttribute("content", page.getContent());
    }

    public static UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
