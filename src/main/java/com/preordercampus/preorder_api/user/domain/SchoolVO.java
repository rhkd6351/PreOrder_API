package com.preordercampus.preorder_api.user.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SCHOOL_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SchoolVO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, updatable = false)
    private Long idx;

    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @Column(name = "description", nullable = true, updatable = true)
    private String description;

    @Column(name = "domain", nullable = false, updatable = true)
    private String domain;

    @Builder
    public SchoolVO(Long idx, String name, String description, String domain) {
        this.idx = idx;
        this.name = name;
        this.description = description;
        this.domain = domain;
    }
}
