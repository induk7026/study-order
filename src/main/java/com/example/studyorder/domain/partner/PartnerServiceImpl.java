package com.example.studyorder.domain.partner;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService{

    /*
     *  추후 데이터 베이스를 변경해야하는 상황에서 변경 가능
     *  도메인 로직은 유지 가능
     *  @Resource(name ="partnerJdbcTemplateImpl")
     *  @Qualifier
     * */
    private final PartnerStore partnerStore;
    private final PartnerReader partnerReader;

    @Override
    @Transactional
    public PartnerInfo registerPartner(PartnerCommand command) {
        var initPartner = command.toEntity();
        var partner = partnerStore.store(initPartner);
        return new PartnerInfo(partner);
    }

    @Override
    public PartnerInfo getPartnerInfo(String partnerToken) {
        Partner partner = partnerReader.getPartner(partnerToken);
        return new PartnerInfo(partner);
    }

    @Override
    @Transactional
    public PartnerInfo enablePartner(String partnerToken) {
        Partner partner = partnerReader.getPartner(partnerToken);
        partner.enable();
        return new PartnerInfo(partner);
    }

    @Override
    @Transactional
    public PartnerInfo disablePartner(String partnerToken) {
        Partner partner = partnerReader.getPartner(partnerToken);
        partner.disable();
        return new PartnerInfo(partner);
    }
}
