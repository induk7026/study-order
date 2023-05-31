package co.kr.order.studyorder.infrastructure.partner;

import co.kr.order.studyorder.common.exception.EntityNotFoundException;
import co.kr.order.studyorder.doamin.partner.Partner;
import co.kr.order.studyorder.doamin.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerReadImpl implements PartnerReader {

    private final PartnerRepository partnerRepository;

    @Override
    public Partner getPartner(Long partnerId) {
        return partnerRepository.findById(partnerId)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Partner getPartner(String partnerToken) {
        return partnerRepository.findByPartnerToken(partnerToken)
            .orElseThrow(EntityNotFoundException::new);
    }
}
