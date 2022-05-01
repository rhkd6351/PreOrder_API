package com.preordercampus.preorder_api.restaurant.domain;


import com.preordercampus.preorder_api.user.domain.SchoolVO;
import com.preordercampus.preorder_api.user.domain.UserVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESTAURANT_CATEGORY_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RestaurantCategoryVO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, updatable = false)
    private Long idx;

    @Column(name = "name", nullable = false, updatable = true)
    private String name;

}
