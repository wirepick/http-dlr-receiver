package com.wirepick.http_dlr_receiver.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;    
import lombok.AllArgsConstructor;
import jakarta.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dlr_from_telco")
public class DlrFromTelco {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "recd_time")
    private LocalDateTime recdTime;

    @Column(name="provider", length = 30)
    private String provider;

    @Column(name="provider_msg_id", length = 50)
    private String providerMsgId;

    @Column(name = "query_string", length = 200)
    private String queryString;

    @Column(name = "processed", nullable = false)
    private String processed = "NEW";

}
