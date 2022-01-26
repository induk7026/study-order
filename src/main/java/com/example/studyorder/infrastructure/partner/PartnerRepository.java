package com.example.studyorder.infrastructure.partner;

import com.example.studyorder.domain.partner.Partner;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Optional<Partner> findByPartnerToken(String partnerToken);
}
