package com.preordercampus.preorder_api.restaurant.repository;


import com.preordercampus.preorder_api.restaurant.domain.RestaurantGroupVO;
import com.preordercampus.preorder_api.restaurant.domain.RestaurantVO;
import com.preordercampus.preorder_api.user.domain.SchoolVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantGroupRepository extends JpaRepository<RestaurantGroupVO, Long> {


    public Optional<RestaurantGroupVO> findByNameAndSchool(String name, SchoolVO school);

    public List<RestaurantGroupVO> findBySchool(SchoolVO schoolVO);

}
