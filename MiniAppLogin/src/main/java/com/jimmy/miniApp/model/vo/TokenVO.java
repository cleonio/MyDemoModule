package com.jimmy.miniApp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenVO {

    private String token;

    //是否绑定手机号：0-未绑定，1已绑定
    private Integer bindMobile;
}
