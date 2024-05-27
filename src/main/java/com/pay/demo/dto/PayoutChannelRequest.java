package com.pay.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PayoutChannelRequest extends CommonRequest {

    @NotBlank
    private String country;

    @NotBlank
    private String currency;

}
