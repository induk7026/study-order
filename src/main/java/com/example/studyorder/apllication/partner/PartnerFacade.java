package com.example.studyorder.apllication.partner;

import com.example.studyorder.domain.notification.NotificationService;
import com.example.studyorder.domain.partner.PartnerCommand;
import com.example.studyorder.domain.partner.PartnerInfo;
import com.example.studyorder.domain.partner.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerFacade {
    private final PartnerService partnerService;
    private final NotificationService notificationService;

    public PartnerInfo registerPartner(PartnerCommand command) {
        var partnerInfo = partnerService.registerPartner(command);
        notificationService.sendEmail(partnerInfo.getEmail(), "title", "description");
        return partnerInfo;
    }

}
