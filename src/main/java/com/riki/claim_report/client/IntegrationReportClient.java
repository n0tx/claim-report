package com.riki.claim_report.client;

import com.riki.claim_report.dto.ClaimRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "integration-report", url = "http://localhost:8081/api")
public interface IntegrationReportClient {

    @PostMapping("/claims")
    void sendDataClaim(@RequestBody ClaimRequest claimRequest);
}
