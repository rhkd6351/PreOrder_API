package com.preordercampus.preorder_api.user.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AUTH_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AuthVO {

    @Id
    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Builder
    public AuthVO(String name) {
        this.name = name;
    }

    public enum Type{
        ROLE_ADMIN("ROLE_ADMIN"),
        ROLE_USER("ROLE_USER");

        final String value;

        Type(String value) {
            this.value = value;
        }
        public String value(){
            return value;
        }
    }

}
