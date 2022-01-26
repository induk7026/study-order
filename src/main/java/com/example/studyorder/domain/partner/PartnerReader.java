package com.example.studyorder.domain.partner;

public interface PartnerReader {
    Partner getPartner(long id);
    Partner getPartner(String partnerToken);
}
