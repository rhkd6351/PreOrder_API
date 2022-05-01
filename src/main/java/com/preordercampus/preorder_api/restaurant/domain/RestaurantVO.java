package com.preordercampus.preorder_api.restaurant.domain;


import com.preordercampus.preorder_api.user.domain.SchoolVO;
import com.preordercampus.preorder_api.user.domain.UserVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESTAURANT_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RestaurantVO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, updatable = false)
    private Long idx;

    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @Lob
    @Column(name = "description", nullable = true, updatable = true)
    private String description;

    @Column(name = "activated", nullable = false, updatable = true)
    private boolean activated;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "state", nullable = false, updatable = true)
    private Long state;

    @ManyToOne(targetEntity = UserVO.class)
    @JoinColumn(name = "owner_fk", nullable = false, updatable = false)
    private UserVO owner;

    @ManyToOne(targetEntity = SchoolVO.class)
    @JoinColumn(name = "school_fk", nullable = false, updatable = false)
    private SchoolVO school;

    @ManyToOne(targetEntity = RestaurantCategoryVO.class)
    @JoinColumn(name = "category_fk", nullable = false, updatable = true)
    private RestaurantCategoryVO category;

    @ManyToOne(targetEntity = RestaurantGroupVO.class)
    @JoinColumn(name = "group_fk", nullable = false, updatable = false)
    private RestaurantGroupVO group;


    public enum State{
        OPENED(1L),
        CLOSED(2L),
        SUSPENDED(3L);

        private Long state;

        State(Long state) {
            this.state = state;
        }

        public Long state(){
            return this.state;
        }

    }

    @Builder
    public RestaurantVO(String name, String description, boolean activated, Long state, UserVO owner, SchoolVO school, RestaurantCategoryVO category, RestaurantGroupVO group) {
        this.name = name;
        this.description = description;
        this.activated = activated;
        this.state = state;
        this.owner = owner;
        this.school = school;
        this.category = category;
        this.group = group;
    }
}
