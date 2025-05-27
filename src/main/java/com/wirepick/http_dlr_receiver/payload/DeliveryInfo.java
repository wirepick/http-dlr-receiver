package com.wirepick.http_dlr_receiver.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryInfo {
    @JsonProperty("address")
    private String address;

    @JsonProperty("deliveryStatus")
    private String deliveryStatus;

}
