package com.CafeHub.Manage.menu.service;


import com.CafeHub.Manage.cafe.repository.CafeRepository;
import com.CafeHub.Manage.menu.entity.Menu;
import com.CafeHub.Manage.menu.repository.MenuRepository;
import com.CafeHub.Manage.menu.request.AllMenuGetRequest;
import com.CafeHub.Manage.menu.request.MenuCreateRequest;
import com.CafeHub.Manage.menu.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService{

    private final MenuRepository menuRepository;

    private final CafeRepository cafeRepository;

    @Override
    public List<MenuResponse> getMenusByCafeId(AllMenuGetRequest request) {
        return menuRepository.findAllByCafeId(request.getCafeId()).stream()
                .map(menu -> new MenuResponse(
                        menu.getCategory(),
                        menu.getName(),
                        menu.getPrice(),
                        menu.getIsBest()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createMenu(MenuCreateRequest request) {

        Menu menu = Menu.builder()
                .category(request.getCategory())
                .name(request.getName())
                .price(request.getPrice())
                .isBest(request.getBest())
                .cafe(cafeRepository.findById(request.getCafeId()).get())
                .build();

        menuRepository.save(menu);
    }


}
