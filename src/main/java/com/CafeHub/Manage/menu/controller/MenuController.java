package com.CafeHub.Manage.menu.controller;

import com.CafeHub.Manage.menu.request.AllMenuGetRequest;
import com.CafeHub.Manage.menu.request.MenuCreatePageRequest;
import com.CafeHub.Manage.menu.request.MenuCreateRequest;
import com.CafeHub.Manage.menu.response.AllMenuGetResponse;
import com.CafeHub.Manage.menu.response.MenuResponse;
import com.CafeHub.Manage.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;



    @GetMapping("/menus/{cafeId}")
    public String MenusPage(@ModelAttribute AllMenuGetRequest request, Model model){


        List<MenuResponse> menus = menuService.getMenusByCafeId(request);
        AllMenuGetResponse response = new AllMenuGetResponse(request.getCafeId(), menus);

        model.addAttribute("response", response);

        return "menus";
    }


    @GetMapping("/menuCreateForm/{cafeId}")
    public String MenuCreatePage(@ModelAttribute MenuCreatePageRequest request, Model model){

        model.addAttribute("response",request.getCafeId());
        return "menuCreateForm";
    }


    @PostMapping("/createMenu/{cafeId}")
    public String MenuCreate(@ModelAttribute MenuCreateRequest request, Model model){


        menuService.createMenu(request);
        model.addAttribute("response",request.getCafeId());
        return "redirect:/menus/"+request.getCafeId();
    }



}
