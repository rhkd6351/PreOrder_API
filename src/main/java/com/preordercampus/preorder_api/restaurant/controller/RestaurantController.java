package com.preordercampus.preorder_api.restaurant.controller;

import com.preordercampus.preorder_api.restaurant.dto.CategoryDTO;
import com.preordercampus.preorder_api.restaurant.dto.CreateRestaurant;
import com.preordercampus.preorder_api.restaurant.dto.RestaurantGroupDTO;
import com.preordercampus.preorder_api.restaurant.service.CategoryFindService;
import com.preordercampus.preorder_api.restaurant.service.RestaurantFindService;
import com.preordercampus.preorder_api.restaurant.service.RestaurantGroupFindService;
import com.preordercampus.preorder_api.restaurant.service.RestaurantUpdateService;
import com.preordercampus.preorder_api.user.domain.SchoolVO;
import com.preordercampus.preorder_api.user.service.SchoolFindService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestaurantController {


    RestaurantUpdateService restaurantUpdateService;
    RestaurantFindService restaurantFindService;
    RestaurantGroupFindService restaurantGroupFindService;
    SchoolFindService schoolFindService;
    CategoryFindService categoryFindService;

    public RestaurantController(RestaurantUpdateService restaurantUpdateService, RestaurantFindService restaurantFindService, RestaurantGroupFindService restaurantGroupFindService, SchoolFindService schoolFindService, CategoryFindService categoryFindService) {
        this.restaurantUpdateService = restaurantUpdateService;
        this.restaurantFindService = restaurantFindService;
        this.restaurantGroupFindService = restaurantGroupFindService;
        this.schoolFindService = schoolFindService;
        this.categoryFindService = categoryFindService;
    }

    @PostMapping("/v1/admin/restaurants")
    public ResponseEntity<CreateRestaurant.Response> saveRestaurant(
            @RequestBody @Validated CreateRestaurant.Request request){
        Long idx = restaurantUpdateService.save(request);

        return new ResponseEntity<>(new CreateRestaurant.Response(idx), HttpStatus.CREATED);
    }

    @GetMapping("/v1/user/school/{school-idx}/restaurants")
    public ResponseEntity<List<RestaurantGroupDTO>> getBySchoolIdx(

            @PathVariable(value = "school-idx") Long schoolIdx){
        SchoolVO school = schoolFindService.findByIdx(schoolIdx);
        List<RestaurantGroupDTO> groups = restaurantGroupFindService.findAllWithRestaurantsBySchool(school);

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/v1/user/restaurants/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        List<CategoryDTO> categories = categoryFindService.findALl().stream().map(CategoryDTO::fromEntity).collect(Collectors.toList());

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/v1/admin/restaurants/groups")
    public ResponseEntity<List<RestaurantGroupDTO>> getGroups(){
        List<RestaurantGroupDTO> groups = restaurantGroupFindService.findALl().stream().map(RestaurantGroupDTO::fromEntitySimple).collect(Collectors.toList());

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }
}
