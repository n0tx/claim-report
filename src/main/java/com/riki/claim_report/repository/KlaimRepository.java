package com.riki.claim_report.repository;

import com.riki.claim_report.model.Klaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlaimRepository extends JpaRepository<Klaim, Long> {
}
