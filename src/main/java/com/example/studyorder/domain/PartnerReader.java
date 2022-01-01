package com.example.studyorder.domain;

public interface PartnerReader {
    Partner getPartner(long id);
    Partner getPartner(String partnerToken);
}
