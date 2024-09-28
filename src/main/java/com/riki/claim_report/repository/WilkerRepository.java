package com.riki.claim_report.repository;

import com.riki.claim_report.model.Wilker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WilkerRepository extends JpaRepository<Wilker, Long> {
}
