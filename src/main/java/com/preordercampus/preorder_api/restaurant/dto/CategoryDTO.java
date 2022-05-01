package com.preordercampus.preorder_api.restaurant.dto;


import com.preordercampus.preorder_api.restaurant.domain.RestaurantCategoryVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CategoryDTO {

    private Long idx;
    private String name;

    @Builder
    public CategoryDTO(Long idx, String name) {
        this.idx = idx;
        this.name = name;
    }

    public static CategoryDTO fromEntity(RestaurantCategoryVO entity){
        return CategoryDTO.builder()
                .idx(entity.getIdx())
                .name(entity.getName())
                .build();
    }
}
