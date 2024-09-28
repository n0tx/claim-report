package com.riki.claim_report.service.impl;

import com.riki.claim_report.model.Claim;
import com.riki.claim_report.repository.ClaimRepository;
import com.riki.claim_report.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	private ClaimRepository claimRepository;

	@Override
	public List<Claim> findClaimAll() {
		/*
		DecimalFormat df = new DecimalFormat("#,###");
		List<Claim> claims = claimRepository.findAll();
		for (Claim claim : claims) {
			// yang ini error, double required, string provide
			// claim.setNilaiBebanKlaim(Double.parseDouble(df.format(claim.getNilaiBebanKlaim())));

			// yang ini tetep aja, 2.7E9 tidak berubah menjadi 2,700,000,000.00 waktu nampilin
			claim.setNilaiBebanKlaim(Double.parseDouble(df.format(claim.getNilaiBebanKlaim()).replace(",", "")));
		}
		return claims;
		 */
		return claimRepository.findAll();
	}
}
