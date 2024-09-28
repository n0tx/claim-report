package com.riki.claim_report.controller;

import com.riki.claim_report.dto.ClaimReportSummary;
import com.riki.claim_report.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @GetMapping("/")
    public String getAllClaims(Model model) {
        List<ClaimReportSummary> claims = claimService.findClaimReportSummary();
        model.addAttribute("claims", claims);
        return "claim/claim-list";
    }

}
