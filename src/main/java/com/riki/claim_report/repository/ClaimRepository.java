package com.riki.claim_report.repository;

import com.riki.claim_report.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findBySubCobIn(List<String> subCobList);
}
