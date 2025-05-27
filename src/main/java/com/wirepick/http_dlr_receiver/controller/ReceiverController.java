package com.wirepick.http_dlr_receiver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.wirepick.http_dlr_receiver.payload.DeliveryInfoNotification;
import com.wirepick.http_dlr_receiver.service.ReceiverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReceiverController {

    private final ReceiverService receiverService;
    private static final Logger LOGGER = LogManager.getLogger(ReceiverController.class);

    @PostMapping("/orangemali")
    public ResponseEntity<String> dlrProcessor(@RequestBody DeliveryInfoNotification dlr) {
        if ((dlr.getCallbackData() == null || dlr.getCallbackData().isEmpty()
                || dlr.getCallbackData().equals("null"))) {
            LOGGER.error("Callback data is null");
            return ResponseEntity.badRequest().body("Callback data is null");
        }
        return receiverService.processRequest(dlr, "orangemalihttp");
    }
}
