package com.riki.claim_report.dto;

import lombok.Data;

@Data
public class Summary {
    // jumlah_terjamin, nilai_beban_klaim
    private int jumlahTerjamin;
    private double nilaiBebanKlaim;

    public Summary(int jumlahTerjamin, double nilaiBebanKlaim) {
        this.jumlahTerjamin = jumlahTerjamin;
        this.nilaiBebanKlaim = nilaiBebanKlaim;
    }
}
