package com.preordercampus.preorder_api.user.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizeUser {

    @Getter
    @Builder
    public static class Request{

        @NotBlank(groups = {ValidationGroups.AuthorizationGroup.class}, message = "아이디가 입력되지 않았습니다.")
        private String email;

        @NotBlank(groups = {ValidationGroups.AuthorizationGroup.class}, message = "비밀번호가 입력되지 않았습니다.")
        private String password;
    }

    @AllArgsConstructor
    @Getter
    public static class Response{
        String accessToken;
    }
}
