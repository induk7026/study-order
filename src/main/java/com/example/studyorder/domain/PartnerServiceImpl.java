package com.example.studyorder.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService{

    private final PartnerStore partnerStore;
    private final PartnerReader partnerReader;

    @Override
    public partnerInfo registerPartner(PartnerCommand command) {

        var initPartner = command.toEntity();

        return null;
    }

    @Override
    public partnerInfo getPartnerInfo(String partnerToken) {
        return null;
    }

    @Override
    public partnerInfo enablePartner(String partnerToken) {
        return null;
    }

    @Override
    public partnerInfo disablePartner(String partnerToken) {
        return null;
    }
}
