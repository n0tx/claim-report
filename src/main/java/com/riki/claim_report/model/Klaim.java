package com.riki.claim_report.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "klaim")
public class Klaim implements Serializable {

    @Serial
    private static final long serialVersionUID = -2717624279840430130L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_cob", nullable = false)
    private String subCob;

    @Column(name = "penyebab_klaim", nullable = false)
    private String penyebabKlaim;

    @Column(name = "periode")
    private LocalDate periode;

    @ManyToOne
    @JoinColumn(name = "id_wilker", nullable = false)
    private Wilker wilker;

    @Column(name = "tgl_keputusan_klaim")
    private LocalDate tglKeputusanKlaim;

    @Column(name = "jumlah_terjamin")
    private int jumlahTerjamin;

    @Column(name = "nilai_beban_klaim")
    private double nilaiBebanKlaim;

    @Column(name = "debet_kredit")
    private int debetKredit;

}

