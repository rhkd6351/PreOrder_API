package com.preordercampus.preorder_api.restaurant.service;

import com.preordercampus.preorder_api.exception.ApiException;
import com.preordercampus.preorder_api.exception.ExceptionEnum;
import com.preordercampus.preorder_api.restaurant.domain.RestaurantGroupVO;
import com.preordercampus.preorder_api.restaurant.dto.RestaurantGroupDTO;
import com.preordercampus.preorder_api.restaurant.repository.RestaurantGroupRepository;
import com.preordercampus.preorder_api.user.domain.SchoolVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantGroupFindService {

    @Autowired
    RestaurantGroupRepository restaurantGroupRepository;


    @Transactional(readOnly = true)
    public List<RestaurantGroupVO> findALl(){
        return restaurantGroupRepository.findAll();
    }

    @Transactional(readOnly = true)
    public RestaurantGroupVO findByIdx(Long idx) {
        return restaurantGroupRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

    @Transactional(readOnly = true)
    public RestaurantGroupVO findByNameAndSchool(String name, SchoolVO school) {
        return restaurantGroupRepository.findByNameAndSchool(name, school).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

    @Transactional(readOnly = true)
    public List<RestaurantGroupDTO> findAllWithRestaurantsBySchool(SchoolVO schoolVO){
        return restaurantGroupRepository.findBySchool(schoolVO).stream().map(
                RestaurantGroupDTO::fromEntity
        ).collect(Collectors.toList());
    }
}
