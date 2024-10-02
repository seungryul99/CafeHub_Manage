package com.CafeHub.Manage.cafe.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CafeUpdateRequest {

    private Long cafeId;

    private String name;

    private String address;

    private String cafePhotoUrl;

    private String phone;

    private String operationHours;

    private String closedDays;

    private String theme;
}

