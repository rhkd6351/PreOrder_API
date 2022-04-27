package com.preordercampus.preorder_api.user.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserVO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, updatable = false)
    private Long idx;

    @Column(name = "email", nullable = false, updatable = false)
    private String email;

    @Column(name = "password", nullable = false, updatable = false)
    private String password;

    @Column(name = "type", nullable = false, updatable = false)
    private String type;

    @Column(name = "activated", nullable = false, updatable = true)
    private boolean activated;

    @Column(name = "oauth", nullable = true, updatable = false)
    private String oauth;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(targetEntity = AuthVO.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_fk", nullable = false, updatable = true)
    private AuthVO auth;

    //create constructor
    @Builder
    public UserVO(String email, String type, boolean activated, String oauth, AuthVO auth) {
        this.email = email;
        this.type = type;
        this.activated = activated;
        this.oauth = oauth;
        this.auth = auth;
    }

    public enum Type{
        USER_STUDENT, USER_RESTAURANT
    }

    public enum Activated{
        DEACTIVATED, ACTIVATED
    }

    public enum Oauth{
        EMAIL, KAKAO, GOOGLE, FACEBOOK
    }
}
