package com.preordercampus.preorder_api.restaurant.domain;


import com.preordercampus.preorder_api.user.domain.SchoolVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RESTAURANT_GROUP_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RestaurantGroupVO {

    public static final String DEFAULT = "default";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, updatable = false)
    private Long idx;

    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @Column(name = "description", nullable = false, updatable = true)
    private String description;

    @OneToOne(targetEntity = SchoolVO.class)
    @JoinColumn(name = "school_fk", nullable = false, updatable = true)
    private SchoolVO school;

    @OneToMany(mappedBy = "group")
    private List<RestaurantVO> restaurants;

}
