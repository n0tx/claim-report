package com.riki.claim_report.service.impl;

import com.riki.claim_report.dto.ClaimReportSummary;
import com.riki.claim_report.dto.Summary;
import com.riki.claim_report.model.Claim;
import com.riki.claim_report.repository.ClaimRepository;
import com.riki.claim_report.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClaimServiceImpl implements ClaimService {
	@Autowired
	private ClaimRepository claimRepository;

	@Override
	public List<ClaimReportSummary> findClaimReportSummary() {
		List<Claim> claims = claimRepository.findAll();
		
		List<ClaimReportSummary> claimReportSummaries = new ArrayList<>();

		// Mengelompokkan berdasarkan sub_cob dan penyebab_klaim
		Map<String, Map<String, Summary>> result = claims.stream()
				.collect(Collectors.groupingBy(Claim::getSubCob,
						Collectors.groupingBy(Claim::getPenyebabKlaim,
								Collectors.collectingAndThen(Collectors.toList(), list -> {
									int jumlahTerjamin = list.stream().mapToInt(Claim::getJumlahTerjamin).sum();
									double nilaiBebanKlaim = list.stream().mapToDouble(Claim::getNilaiBebanKlaim).sum();
									return new Summary(jumlahTerjamin, nilaiBebanKlaim);
								})
						)
				));

		// Untuk menghitung total keseluruhan
		int totalJumlahTerjamin = 0;
		double totalNilaiBebanKlaim = 0;

		// Menampilkan hasil per penyebab_klaim dan subtotal per sub_cob
		for (Map.Entry<String, Map<String, Summary>> subCobEntry : result.entrySet()) {
			String subCob = subCobEntry.getKey();
			Map<String, Summary> penyebabKlaimMap = subCobEntry.getValue();
			int subtotalJumlahTerjamin = 0;
			double subtotalNilaiBebanKlaim = 0;

			for (Map.Entry<String, Summary> penyebabEntry : penyebabKlaimMap.entrySet()) {
				String penyebabKlaim = penyebabEntry.getKey();
				Summary summary = penyebabEntry.getValue();

				// per row untuk setiap penyebab klaim
				claimReportSummaries.add(new ClaimReportSummary(subCob, penyebabKlaim, summary.getJumlahTerjamin(), summary.getNilaiBebanKlaim()));
				

				subtotalJumlahTerjamin += summary.getJumlahTerjamin();
				subtotalNilaiBebanKlaim += summary.getNilaiBebanKlaim();
			}

			// subtotal untuk setiap sub_cob
			claimReportSummaries.add(new ClaimReportSummary("Subtotal " + subCob, "", subtotalJumlahTerjamin, subtotalNilaiBebanKlaim));

			totalJumlahTerjamin += subtotalJumlahTerjamin;
			totalNilaiBebanKlaim += subtotalNilaiBebanKlaim;
		}

		// Total keseluruhan
		claimReportSummaries.add(new ClaimReportSummary("Total ", "", totalJumlahTerjamin, totalNilaiBebanKlaim));
		return claimReportSummaries;
	}

}
