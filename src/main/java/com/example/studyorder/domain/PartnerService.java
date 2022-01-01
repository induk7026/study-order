package com.example.studyorder.domain;

public interface PartnerService {

    partnerInfo registerPartner(PartnerCommand command);
    partnerInfo getPartnerInfo(String partnerToken);
    partnerInfo enablePartner(String partnerToken);
    partnerInfo disablePartner(String partnerToken);
}
