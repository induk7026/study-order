package com.example.studyorder.infrastructure.partner;

import com.example.studyorder.common.exception.EntityNotFoundException;
import com.example.studyorder.domain.partner.Partner;
import com.example.studyorder.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerReaderImpl implements PartnerReader {

    private final PartnerRepository partnerRepository;

    @Override
    public Partner getPartner(long id) {
        return partnerRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Partner getPartner(String partnerToken) {
        return partnerRepository.findByPartnerToken(partnerToken)
            .orElseThrow(EntityNotFoundException::new);
    }
}
