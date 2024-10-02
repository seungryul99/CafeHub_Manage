package com.CafeHub.Manage.menu.request;

import com.CafeHub.Manage.menu.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuCreateRequest {

    private Long cafeId;

    private Category category;

    private String name;

    private Integer price;

    private Boolean best;


}
