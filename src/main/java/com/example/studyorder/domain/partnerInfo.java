package com.example.studyorder.domain;

import com.example.studyorder.domain.Partner.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class partnerInfo {

    private final Long id;
    private final String partnerToken;
    private final String partnerName;
    private final String businessNo;
    private final String email;
    private final Status status;

}
