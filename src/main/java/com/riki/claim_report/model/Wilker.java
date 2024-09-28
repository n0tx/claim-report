package com.riki.claim_report.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wilker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idWilker;

    @NotBlank(message = "Nama Wilayah Kerja tidak boleh kosong")
    private String namaWilker;

}






