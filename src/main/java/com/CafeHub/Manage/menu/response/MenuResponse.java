package com.CafeHub.Manage.menu.response;

import com.CafeHub.Manage.menu.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {

    private Category category;

    private String name;

    private Integer price;

    private Boolean best;
}
