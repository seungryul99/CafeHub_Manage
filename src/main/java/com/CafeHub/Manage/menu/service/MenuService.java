package com.CafeHub.Manage.menu.service;

import com.CafeHub.Manage.menu.request.AllMenuGetRequest;
import com.CafeHub.Manage.menu.request.MenuCreateRequest;
import com.CafeHub.Manage.menu.response.MenuResponse;

import java.util.List;

public interface MenuService {
    List<MenuResponse> getMenusByCafeId(AllMenuGetRequest request);

    void createMenu(MenuCreateRequest request);
}
