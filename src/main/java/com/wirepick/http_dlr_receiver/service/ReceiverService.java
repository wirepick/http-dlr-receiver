package com.wirepick.http_dlr_receiver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wirepick.http_dlr_receiver.entity.DlrFromTelco;
import com.wirepick.http_dlr_receiver.payload.DeliveryInfoNotification;
import com.wirepick.http_dlr_receiver.repo.DlrFromTelcoRepo;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceiverService {

    private static final Logger LOGGER = LogManager.getLogger(ReceiverService.class);
    private final DlrFromTelcoRepo dlrFromTelcoRepo;

    public ResponseEntity<String> processRequest(DeliveryInfoNotification dlr, String provider) {

        try {
            LOGGER.info("persisting the DLR request received: {}", dlr);
            // Save the DLR request here
            DlrFromTelco dlrFromTelco = new DlrFromTelco();
            dlrFromTelco.setRecdTime(LocalDateTime.now());
            dlrFromTelco.setProvider(provider);
            dlrFromTelco.setProviderMsgId(dlr.getCallbackData());
            dlrFromTelco.setQueryString(dlr.getDeliveryInfo().getDeliveryStatus());
            dlrFromTelcoRepo.save(dlrFromTelco);
            LOGGER.info("DLR request persisted successfully: {}", dlrFromTelco);
            return ResponseEntity.ok("DLR received");
        } catch (Exception e) {
            LOGGER.error("Error persisting DLR request: {}", e.getMessage());
            return ResponseEntity.status(500).body("Error persisting DLR request");
        }
    }
}
