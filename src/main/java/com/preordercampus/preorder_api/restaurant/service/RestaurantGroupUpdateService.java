package com.preordercampus.preorder_api.restaurant.service;

import com.preordercampus.preorder_api.exception.ApiException;
import com.preordercampus.preorder_api.exception.ExceptionEnum;
import com.preordercampus.preorder_api.restaurant.domain.RestaurantGroupVO;
import com.preordercampus.preorder_api.restaurant.repository.RestaurantGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantGroupUpdateService {

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
}
