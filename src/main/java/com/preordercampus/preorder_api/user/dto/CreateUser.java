package com.preordercampus.preorder_api.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateUser {

    @Builder
    @Getter
    public class Request{
        @NotBlank
        private String email;

        @NotBlank
        private String password;

        @NotBlank
        private String oauth;
    }

    @Getter
    public class Response{
        Long id;

        public Response(Long id) {
            this.id = id;
        }
    }
}
