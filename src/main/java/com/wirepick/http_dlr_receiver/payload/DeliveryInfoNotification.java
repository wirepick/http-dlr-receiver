package com.wirepick.http_dlr_receiver.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInfoNotification {
    
    @JsonProperty("callbackData")
    private String callbackData;

    @JsonProperty("deliveryInfo")
    private DeliveryInfo deliveryInfo;

    // Constructors

}
