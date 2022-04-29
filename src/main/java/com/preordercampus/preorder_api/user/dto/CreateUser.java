package com.preordercampus.preorder_api.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateUser {

    @Getter
    @Builder
    public static class Request{

        @NotBlank(message = "email이 입력되지 않았습니다.")
        @Email(message = "이메일 형식이 잘못되었습니다.")
        private String email;

        @NotBlank(message = "password가 입력되지 않았습니다.")
        @Size(min = 8, message = "비밀번호는 최소 8자 이상 이어야 합니다.")
        private String password;

        @NotBlank(message = "OAuth 정보가 입력되지 않았습니다.")
        private String oauth;

        @NotNull(message = "school Idx 값이 입력되지 않았습니다.")
        private Long schoolIdx;

        @NotBlank(message = "nickname이 입력되지 않았습니다.")
        private String nickname;
    }

    @Getter
    @AllArgsConstructor
    public static class Response{
        Long id;
    }
}
