package com.CafeHub.Manage.cafe.controller;


import com.CafeHub.Manage.cafe.request.*;
import com.CafeHub.Manage.cafe.response.AllCafeGetResponse;
import com.CafeHub.Manage.cafe.response.CafeInfoResponse;
import com.CafeHub.Manage.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;


    @GetMapping("/")
    public String index(){

        return "redirect:/cafes";
    }


    // 초기 개발 단계라서 페이징을 추후 할 수 있게 해두고 우선은 100개씩 받아옴, 초기 입력 데이터가 100개를 넘지 않을거 같음
    @GetMapping("/cafes")
    public String getAllCafe(@RequestParam(defaultValue = "1", name = "page") int page,
                             @RequestParam(defaultValue = "100", name = "size") int size,
                             Model model){

        AllCafeGetRequest request = new AllCafeGetRequest(page,size);
        AllCafeGetResponse response = cafeService.getAllCafeList(request);

        model.addAttribute("response", response);
        return "cafes";
    }


    @GetMapping("/cafes/{cafeId}")
    public String getCafeInfo(@PathVariable("cafeId") Long cafeId, Model model){

        CafeInfoRequest request = new CafeInfoRequest(cafeId);
        CafeInfoResponse response = cafeService.getCafeInfo(request);

        model.addAttribute("response", response);
        return "cafeInfo";
    }


    @GetMapping("/cafeCreateForm")
    public String cafeCreateForm(){

        return "cafeCreateForm";
    }


    @PostMapping(value = "/createCafe", consumes = "multipart/form-data")
    public String createCafe(@ModelAttribute CafeCreateRequest request) throws IOException {

        Long createdCafeId = cafeService.createNewCafe(request);
        return "redirect:/cafes/" + createdCafeId;
    }


    @GetMapping("/cafeUpdateForm")
    public String cafeUpdateForm(@ModelAttribute CafeUpdateRequest request, Model model){

        // cafeInfo 에서 넘겨준걸 그대로 넘겨주는거라 request, response 구분 없이함
        model.addAttribute("response",request);
        return "cafeUpdateForm";
    }

    @PostMapping("/updateCafe")
    public String updateCafe(@ModelAttribute CafeUpdateRequest request){

        cafeService.updateCafe(request);

        return "redirect:/cafes/"+request.getCafeId();
    }

    @PostMapping("/deleteCafe")
    public String deleteCafe(@RequestParam("cafeId") Long cafeId){

        CafeDeleteRequest request = new CafeDeleteRequest(cafeId);
        cafeService.deleteCafe(request);

        return "redirect:/cafes";
    }


}
