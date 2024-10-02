package com.CafeHub.Manage.cafe.response;

import com.CafeHub.Manage.cafe.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CafeResponse {

    private Long cafeId;

    private String cafeName;

    private Theme cafeTheme;
}
