package com.preordercampus.preorder_api.restaurant.repository;


import com.preordercampus.preorder_api.restaurant.domain.RestaurantCategoryVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategoryVO, Long> {


}
