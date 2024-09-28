package com.riki.claim_report.service;

import com.riki.claim_report.dto.ClaimReportSummary;

import java.util.List;

public interface ClaimService {

	List<ClaimReportSummary> findClaimReportSummary();
	
}
