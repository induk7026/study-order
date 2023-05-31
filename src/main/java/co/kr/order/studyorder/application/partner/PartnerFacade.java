package co.kr.order.studyorder.application.partner;

import co.kr.order.studyorder.doamin.partner.PartnerInfo;
import co.kr.order.studyorder.doamin.partner.PartnerService;
import co.kr.order.studyorder.doamin.partner.command.PartnerCommand;
import co.kr.order.studyorder.doamin.partner.command.UpdatePartnerCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerFacade {

    private final PartnerService partnerService;

    public PartnerInfo registerPartner(PartnerCommand command) {
        var partnerInfo = partnerService.registerPartner(command);
        return partnerInfo;
    }

    public void updatePartner(UpdatePartnerCommand command) {
        partnerService.enablePartner(command.getPartnerToken());
    }
}
