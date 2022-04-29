package com.preordercampus.preorder_api.user.dto;


import com.preordercampus.preorder_api.user.domain.UserVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserDTO {

    private Long idx;

    private String email;

    private String password;

    private String nickname;

    private String type;

    private String oauth;

    private LocalDateTime createdAt;

    private SchoolDTO school;

    @Builder
    public UserDTO(Long idx, String email, String password, String nickname, String type, String oauth, LocalDateTime createdAt, SchoolDTO school) {
        this.idx = idx;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.type = type;
        this.oauth = oauth;
        this.createdAt = createdAt;
        this.school = school;
    }

    public static UserDTO fromEntity(UserVO entity){
        return UserDTO.builder()
                .email(entity.getEmail())
                .school(SchoolDTO.formEntity(entity.getSchool()))
                .createdAt(entity.getCreatedAt())
                .oauth(entity.getOauth())
                .nickname(entity.getNickname())
                .type(entity.getType())
                .build();
    }

}
