package com.preordercampus.preorder_api.restaurant.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateRestaurant {

    @Getter
    @Builder
    public static class Request{
        @NotBlank
        private String name;

        @NotBlank
        private String description;

        @NotNull
        private Long categoryIdx;

        @NotNull
        private Long groupIdx;

        @NotNull
        private Long schoolIdx;

        @NotNull
        private Long userIdx;
    }

    @Getter
    public static class Response{
        private Long idx;

        public Response(Long idx) {
            this.idx = idx;
        }
    }


}
