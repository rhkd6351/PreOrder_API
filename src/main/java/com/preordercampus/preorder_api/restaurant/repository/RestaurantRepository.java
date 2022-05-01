package com.preordercampus.preorder_api.restaurant.repository;


import com.preordercampus.preorder_api.restaurant.domain.RestaurantVO;
import com.preordercampus.preorder_api.user.domain.SchoolVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantVO, Long> {


}
