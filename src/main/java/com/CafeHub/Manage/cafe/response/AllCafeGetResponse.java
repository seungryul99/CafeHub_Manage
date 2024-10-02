package com.CafeHub.Manage.cafe.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class    AllCafeGetResponse {

    private List<CafeResponse> cafeResponses;

    private int totalPage;

    private int currentPage;

    private int size;

    private boolean isFirst;

    private boolean isLast;
}
