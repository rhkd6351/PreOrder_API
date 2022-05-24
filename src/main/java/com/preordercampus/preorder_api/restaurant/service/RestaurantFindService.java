package com.preordercampus.preorder_api.restaurant.service;

import com.preordercampus.preorder_api.restaurant.domain.RestaurantVO;
import com.preordercampus.preorder_api.restaurant.repository.RestaurantRepository;
import com.preordercampus.preorder_api.user.domain.SchoolVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantFindService {


    public final RestaurantRepository restaurantRepository;


    @Transactional(readOnly = true)
    public List<RestaurantVO> getRestaurantBySchoolAndCategory(
            SchoolVO schoolVO, Long categoryIdx
    ) {
        return restaurantRepository.findBySchoolAndCategory(schoolVO, categoryIdx);
    }


}



