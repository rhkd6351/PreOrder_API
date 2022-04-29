package com.preordercampus.preorder_api.user.dto;


import com.preordercampus.preorder_api.user.domain.SchoolVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SchoolDTO {


    private Long idx;

    private String name;

    private String description;

    private String domain;

    @Builder
    public SchoolDTO(Long idx, String name, String description, String domain) {
        this.idx = idx;
        this.name = name;
        this.description = description;
        this.domain = domain;
    }

    public static SchoolDTO formEntity(SchoolVO entity) {
        return SchoolDTO.builder()
                .idx(entity.getIdx())
                .name(entity.getName())
                .description(entity.getDescription())
                .domain(entity.getDomain())
                .build();
    }
}
