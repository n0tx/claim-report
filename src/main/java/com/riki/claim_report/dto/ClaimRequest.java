package com.riki.claim_report.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ClaimRequest {
    private String lob;
    private String penyebabKlaim;
    private LocalDate periode;
    private double nilaiBebanKlaim;
}