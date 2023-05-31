package co.kr.order.studyorder.doamin.partner;

import co.kr.order.studyorder.doamin.partner.command.PartnerCommand;

public interface PartnerService {

    PartnerInfo registerPartner(PartnerCommand command);
    PartnerInfo getPartnerInfo(String partnerToken);
    PartnerInfo enablePartner(String partnerToken);
    PartnerInfo disablePartner(String partnerToken);
}
