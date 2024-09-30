package com.riki.claim_report.service;

import com.riki.claim_report.client.IntegrationReportClient;
import com.riki.claim_report.dto.ClaimReportSummary;
import com.riki.claim_report.dto.ClaimRequest;
import com.riki.claim_report.model.Claim;
import com.riki.claim_report.model.Wilker;
import com.riki.claim_report.repository.ClaimRepository;
import com.riki.claim_report.service.impl.ClaimServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClaimServiceTest {
    @Mock
    private ClaimRepository claimRepository;
    @Mock
    private IntegrationReportClient integrationReportClient;
    @InjectMocks
    private ClaimServiceImpl claimService;

    public ClaimServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    // Test rekap daftar klaim berdasarkan sub_cob dan penyebab_klaim
    @Test
    void testFindClaimReportSummary() {

        List<Claim> claims = new ArrayList<>();

        Claim claim1 = new Claim();
        claim1.setSubCob("Produktif");
        claim1.setPenyebabKlaim("Macet");
        claim1.setPeriode(LocalDate.parse("2023-01-31"));
        claim1.setWilker(new Wilker(2L, "Kantor Cabang Jakarta"));
        claim1.setTglKeputusanKlaim(LocalDate.parse("2023-02-01"));
        claim1.setJumlahTerjamin(1);
        claim1.setNilaiBebanKlaim(10.0);
        claims.add(claim1);

        Claim claim2 = new Claim();
        claim2.setSubCob("Produktif");
        claim2.setPenyebabKlaim("Macet");
        claim2.setPeriode(LocalDate.parse("2023-01-31"));
        claim2.setWilker(new Wilker(2L, "Kantor Cabang Jakarta"));
        claim2.setTglKeputusanKlaim(LocalDate.parse("2023-02-01"));
        claim2.setJumlahTerjamin(1);
        claim2.setNilaiBebanKlaim(10.0);
        claims.add(claim2);

        Claim claim3 = new Claim();
        claim3.setSubCob("Produktif");
        claim3.setPenyebabKlaim("Macet");
        claim3.setPeriode(LocalDate.parse("2023-01-31"));
        claim3.setWilker(new Wilker(2L, "Kantor Cabang Jakarta"));
        claim3.setTglKeputusanKlaim(LocalDate.parse("2023-02-01"));
        claim3.setJumlahTerjamin(1);
        claim3.setNilaiBebanKlaim(10.0);
        claims.add(claim3);

        Claim claim4 = new Claim();
        claim4.setSubCob("Produktif");
        claim4.setPenyebabKlaim("Meninggal");
        claim4.setPeriode(LocalDate.parse("2023-01-31"));
        claim4.setWilker(new Wilker(2L, "Kantor Cabang Jakarta"));
        claim4.setTglKeputusanKlaim(LocalDate.parse("2023-02-01"));
        claim4.setJumlahTerjamin(1);
        claim4.setNilaiBebanKlaim(10.0);
        claims.add(claim4);

        Claim claim5 = new Claim();
        claim5.setSubCob("Produktif");
        claim5.setPenyebabKlaim("Meninggal");
        claim5.setPeriode(LocalDate.parse("2023-01-31"));
        claim5.setWilker(new Wilker(2L, "Kantor Cabang Jakarta"));
        claim5.setTglKeputusanKlaim(LocalDate.parse("2023-02-01"));
        claim5.setJumlahTerjamin(1);
        claim5.setNilaiBebanKlaim(10.0);
        claims.add(claim5);

        List<ClaimReportSummary> claimReportSummaries = new ArrayList<>();
        claimReportSummaries.add(new ClaimReportSummary("Produktif", "Meninggal", 2, 20.0));
        claimReportSummaries.add(new ClaimReportSummary("Produktif", "Macet", 3, 30.0));
        claimReportSummaries.add(new ClaimReportSummary("Subtotal Produktif", "", 5, 50.0));
        claimReportSummaries.add(new ClaimReportSummary("Total", "", 5, 50.0));

        when(claimRepository.findAll()).thenReturn(claims);

        List<ClaimReportSummary> foundClaimReportSummaries = claimService.findClaimReportSummary();

        assertNotNull(foundClaimReportSummaries);
        assertEquals(claimReportSummaries.size(), foundClaimReportSummaries.size());

        assertEquals(claimReportSummaries.get(0).getJumlahTerjamin(), foundClaimReportSummaries.get(0).getJumlahTerjamin());
        assertEquals(claimReportSummaries.get(2).getNilaiBebanKlaim(), foundClaimReportSummaries.get(2).getNilaiBebanKlaim());
    }

    // Test kirim data ke aplikasi eksternal
    @Test
    void testSendDataClaim_Success() {

        // Data klaim yang akan dikirim
        Claim claim1 = new Claim();
        claim1.setSubCob("PEN");
        claim1.setPenyebabKlaim("Macet");
        claim1.setPeriode(LocalDate.parse("2023-01-31"));
        claim1.setNilaiBebanKlaim(100.0);

        Claim claim2 = new Claim();
        claim2.setSubCob("KUR");
        claim2.setPenyebabKlaim("Meninggal");
        claim2.setPeriode(LocalDate.parse("2023-01-31"));
        claim2.setNilaiBebanKlaim(150.0);
        List<Claim> claims = Arrays.asList(claim1, claim2);

        ClaimRequest claimRequest1 = new ClaimRequest();
        claimRequest1.setLob("PEN");
        claimRequest1.setPenyebabKlaim("Macet");
        claimRequest1.setPeriode(LocalDate.parse("2023-01-31"));
        claimRequest1.setNilaiBebanKlaim(100.0);

        ClaimRequest claimRequest2 = new ClaimRequest();
        claimRequest2.setLob("KUR");
        claimRequest2.setPenyebabKlaim("Meninggal");
        claimRequest2.setPeriode(LocalDate.parse("2023-01-31"));
        claimRequest2.setNilaiBebanKlaim(150.0);
        List<ClaimRequest> claimRequests = Arrays.asList(claimRequest1, claimRequest2);

        // Memastikan tidak ada exception saat pengiriman
        assertDoesNotThrow(() -> claimService.sendListDataClaim(claims));

        // Verifikasi bahwa FeignClient dipanggil untuk setiap klaim
        verify(integrationReportClient, times(1)).sendDataClaim(claimRequest1);
        verify(integrationReportClient, times(1)).sendDataClaim(claimRequest2);
    }

}


