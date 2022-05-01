package com.preordercampus.preorder_api.restaurant.service;

import com.preordercampus.preorder_api.restaurant.domain.RestaurantCategoryVO;
import com.preordercampus.preorder_api.restaurant.domain.RestaurantGroupVO;
import com.preordercampus.preorder_api.restaurant.domain.RestaurantVO;
import com.preordercampus.preorder_api.restaurant.dto.CreateRestaurant;
import com.preordercampus.preorder_api.restaurant.repository.RestaurantRepository;
import com.preordercampus.preorder_api.user.domain.SchoolVO;
import com.preordercampus.preorder_api.user.domain.UserVO;
import com.preordercampus.preorder_api.user.service.SchoolFindService;
import com.preordercampus.preorder_api.user.service.UserFindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantUpdateService {


    RestaurantRepository restaurantRepository;

    CategoryFindService categoryFindService;
    RestaurantGroupFindService restaurantGroupFindService;
    SchoolFindService schoolFindService;
    UserFindService userFindService;

    public RestaurantUpdateService(RestaurantRepository restaurantRepository, CategoryFindService categoryFindService, RestaurantGroupFindService restaurantGroupFindService, SchoolFindService schoolFindService, UserFindService userFindService) {
        this.restaurantRepository = restaurantRepository;
        this.categoryFindService = categoryFindService;
        this.restaurantGroupFindService = restaurantGroupFindService;
        this.schoolFindService = schoolFindService;
        this.userFindService = userFindService;
    }

    @Transactional
    public Long save(CreateRestaurant.Request request){

        RestaurantCategoryVO category = categoryFindService.findByIdx(request.getCategoryIdx());
        SchoolVO school = schoolFindService.findByIdx(request.getSchoolIdx());
        UserVO user = userFindService.findByIdx(request.getUserIdx());
        RestaurantGroupVO group;

        if(request.getGroupIdx() == 0)
            group = restaurantGroupFindService.findByNameAndSchool(RestaurantGroupVO.DEFAULT, school);
        else
            group = restaurantGroupFindService.findByIdx(request.getGroupIdx());

        RestaurantVO restaurant = RestaurantVO.builder()
                .school(school)
                .name(request.getName())
                .activated(true)
                .description(request.getDescription())
                .category(category)
                .owner(user)
                .state(RestaurantVO.State.CLOSED.state())
                .group(group)
                .build();

        RestaurantVO savedRestaurant = restaurantRepository.save(restaurant);
        return savedRestaurant.getIdx();
    }


}
