package com.riki.claim_report.controller;

import com.riki.claim_report.model.Claim;
import com.riki.claim_report.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @GetMapping("/")
    public String getAllClaims(Model model) {
        List<Claim> claims = claimService.findClaimAll();
        model.addAttribute("claims", claims);
        return "claim/claim-list";
    }

}
