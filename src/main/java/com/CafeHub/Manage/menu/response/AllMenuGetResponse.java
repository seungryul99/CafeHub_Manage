package com.CafeHub.Manage.menu.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllMenuGetResponse {

    private Long cafeId;

    private List<MenuResponse> menus;

}
