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
            DlrFromTelco existingDlr = dlrFromTelcoRepo.findByProviderAndProviderMsgId(provider, dlr.getCallbackData()).orElse(null);
            if (existingDlr != null) {
                LOGGER.info(" Existing DLR found for provider: {} and message ID: {}, proceeding with update", provider,
                        dlr.getCallbackData());
                existingDlr.setRecdTime(LocalDateTime.now());
                existingDlr.setStatus(dlr.getDeliveryInfo().getDeliveryStatus());
                existingDlr.setQueryString(dlr.toString());
                dlrFromTelcoRepo.save(existingDlr);
                LOGGER.info("DLR request persisted successfully: {}", existingDlr);
                return ResponseEntity.ok("DLR received");
            } else {
                LOGGER.warn("No existing DLR found for provider: {} and message ID: {}, , proceeding with creation", provider,
                        dlr.getCallbackData());
                DlrFromTelco dlrFromTelco = new DlrFromTelco();
                dlrFromTelco.setRecdTime(LocalDateTime.now());
                dlrFromTelco.setProvider(provider);
                dlrFromTelco.setProviderMsgId(dlr.getCallbackData());
                dlrFromTelco.setStatus(dlr.getDeliveryInfo().getDeliveryStatus());
                dlrFromTelco.setQueryString(dlr.toString());
                dlrFromTelcoRepo.save(dlrFromTelco);
                LOGGER.info("DLR request persisted successfully: {}", dlrFromTelco);
                return ResponseEntity.ok("DLR received");
            }
        } catch (Exception e) {
            LOGGER.error("Error persisting DLR request: {}", e.getMessage());
            return ResponseEntity.status(500).body("Error persisting DLR request");
        }
    }
}
