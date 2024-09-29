package com.riki.claim_report.service;

import com.riki.claim_report.dto.ClaimReportSummary;
import com.riki.claim_report.model.Claim;

import java.util.List;

public interface ClaimService {

	List<ClaimReportSummary> findClaimReportSummary();

	void sendDataClaim(Claim claim);

	void sendListDataClaim(List<Claim> claims);

	List<Claim> getClaimsBySubCob();
	
}
