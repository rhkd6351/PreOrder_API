package com.preordercampus.preorder_api.user.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserVO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, updatable = false)
    private Long idx;

    @Column(name = "email", nullable = false, updatable = false)
    private String email;

    @Column(name = "password", nullable = false, updatable = false)
    private String password;

    @Column(name = "nickname", nullable = false, updatable = true)
    private String nickname;

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

    @Column(name = "verify_code", nullable = true, updatable = true)
    private String verifyCode;

    @OneToOne(targetEntity = SchoolVO.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "school_fk", nullable = false, updatable = true)
    private SchoolVO school;


    //create constructor
    @Builder
    public UserVO(String email, String password, String nickname, String type, boolean activated, String oauth, String verifyCode, AuthVO auth, SchoolVO school) {
        this.email = email;
        this.password = password;
        this.type = type;
        this.activated = activated;
        this.oauth = oauth;
        this.auth = auth;
        this.verifyCode = verifyCode;
        this.school = school;
        this.nickname = nickname;
    }

    public enum Type{
        USER_STUDENT("STUDENT"),
        USER_RESTAURANT("RESTAURANT");

        final String value;

        Type(String value) {
            this.value = value;
        }
        public String value(){
            return value;
        }
    }

    public enum Activated{
        INACTIVATED, ACTIVATED
    }

    public enum Oauth{
        EMAIL("EMAIL"),
        KAKAO("KAKAO"),
        GOOGLE("GOOGLE"),
        FACEBOOK("FACEBOOK");

        final String value;

        Oauth(String value) {
            this.value = value;
        }
        public String value(){
            return value;
        }
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public void activateUser(){
        this.activated = true;
    }
}
