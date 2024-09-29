package com.riki.claim_report.controller;

import com.riki.claim_report.dto.ClaimReportSummary;
import com.riki.claim_report.model.Claim;
import com.riki.claim_report.service.ClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ClaimController {

    private static final Logger logger = LoggerFactory.getLogger(ClaimController.class);

    @Autowired
    private ClaimService claimService;

    @GetMapping("/")
    public String getAllClaims(Model model) {
        List<ClaimReportSummary> claims = claimService.findClaimReportSummary();
        model.addAttribute("claims", claims);
        return "claim/claim-list";
    }

    @PostMapping("/send-claims")
    public String sendListDataClaim(Model model) {
        List<Claim> claims = claimService.getClaimsBySubCob();

        try {
            logger.info("Data klaim dikirim");
            logger.info("Tanggal kirim: {}", LocalDate.now());

            claimService.sendListDataClaim(claims);
        } catch (Exception e) {
            logger.error("Pengiriman klaim gagal", e);
            model.addAttribute("message", "Data klaim gagal dikirim " + e.getMessage());
            return "claim/failed";
        }

        logger.info("Data klaim selesai dikirim");
        logger.info("Total klaim: {}", claims.size());
        logger.info("Status pengiriman: berhasil");

        model.addAttribute("message", "Data klaim berhasil dikirim.");
        return "claim/success";
    }

}
