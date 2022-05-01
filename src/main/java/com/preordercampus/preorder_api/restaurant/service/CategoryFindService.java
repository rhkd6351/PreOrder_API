package com.preordercampus.preorder_api.restaurant.service;

import com.preordercampus.preorder_api.exception.ApiException;
import com.preordercampus.preorder_api.exception.ExceptionEnum;
import com.preordercampus.preorder_api.restaurant.domain.RestaurantCategoryVO;
import com.preordercampus.preorder_api.restaurant.repository.RestaurantCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryFindService {

    @Autowired
    RestaurantCategoryRepository restaurantCategoryRepository;


    @Transactional(readOnly = true)
    public List<RestaurantCategoryVO> findALl(){
        return restaurantCategoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public RestaurantCategoryVO findByIdx(Long idx){
        return restaurantCategoryRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }


}
