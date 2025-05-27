package com.wirepick.http_dlr_receiver.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wirepick.http_dlr_receiver.entity.DlrFromTelco;

public interface DlrFromTelcoRepo extends JpaRepository<DlrFromTelco, Integer> {

    
    Optional<DlrFromTelco> findByProviderAndProviderMsgId(String provider, String providerMsgId);


}
