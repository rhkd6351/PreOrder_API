package com.preordercampus.preorder_api.restaurant.dto;


import com.preordercampus.preorder_api.restaurant.domain.RestaurantCategoryVO;
import com.preordercampus.preorder_api.restaurant.domain.RestaurantGroupVO;
import com.preordercampus.preorder_api.restaurant.domain.RestaurantVO;
import com.preordercampus.preorder_api.user.domain.SchoolVO;
import com.preordercampus.preorder_api.user.domain.UserVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RestaurantDTO {

    private Long idx;

    private String name;

    private String description;

    private boolean activated;

    private LocalDateTime createdAt;

    private Long state;

    private CategoryDTO category;

//    private UserVO owner;

//    private SchoolVO school;

//    private RestaurantGroupVO group;

    @Builder
    public RestaurantDTO(Long idx, String name, String description, boolean activated, LocalDateTime createdAt, Long state, CategoryDTO category) {
        this.idx = idx;
        this.name = name;
        this.description = description;
        this.activated = activated;
        this.createdAt = createdAt;
        this.state = state;
        this.category = category;
    }

    public static RestaurantDTO fromEntity(RestaurantVO entity){
        return RestaurantDTO.builder()
                .idx(entity.getIdx())
                .activated(entity.isActivated())
                .description(entity.getDescription())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .state(entity.getState())
                .category(CategoryDTO.fromEntity(entity.getCategory()))
                .build();
    }



}
