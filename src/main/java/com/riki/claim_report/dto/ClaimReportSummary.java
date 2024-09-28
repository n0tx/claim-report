package com.riki.claim_report.dto;

import lombok.Data;

@Data
public class ClaimReportSummary {
    // sub_cob, penyebab_klaim, jumlah_terjamin, nilai_beban_klaim
    private String subCob;
    private String penyebabKlaim;
    private int jumlahTerjamin;
    private double nilaiBebanKlaim;

    public ClaimReportSummary(String subCob, String penyebabKlaim, int jumlahTerjamin, Double nilaiBebanKlaim) {
        this.subCob = subCob;
        this.penyebabKlaim = penyebabKlaim;
        this.jumlahTerjamin = jumlahTerjamin;
        this.nilaiBebanKlaim = nilaiBebanKlaim;
    }
}
