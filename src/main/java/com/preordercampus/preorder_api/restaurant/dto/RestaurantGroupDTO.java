package com.preordercampus.preorder_api.restaurant.dto;


import com.preordercampus.preorder_api.restaurant.domain.RestaurantGroupVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RestaurantGroupDTO {

    private Long idx;

    private String name;

    private String description;

    private List<RestaurantDTO> restaurants;

    @Builder
    public RestaurantGroupDTO(Long idx, String name, String description, List<RestaurantDTO> restaurants) {
        this.idx = idx;
        this.name = name;
        this.description = description;
        this.restaurants = restaurants;
    }

    public static RestaurantGroupDTO fromEntity(RestaurantGroupVO groupVO){
        return RestaurantGroupDTO.builder()
                .idx(groupVO.getIdx())
                .description(groupVO.getDescription())
                .name(groupVO.getName())
                .restaurants(groupVO.getRestaurants().stream().map(RestaurantDTO::fromEntity).collect(Collectors.toList()))
                .build();
    }

    public static RestaurantGroupDTO fromEntitySimple(RestaurantGroupVO groupVO){
        return RestaurantGroupDTO.builder()
                .idx(groupVO.getIdx())
                .description(groupVO.getDescription())
                .name(groupVO.getName())
                .build();
    }
}
