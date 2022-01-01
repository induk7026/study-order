package com.example.studyorder.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PartnerCommand {
    private String email;
    private String businessNo;
    private String partnerName;

    public Partner toEntity(){
        return Partner.builder()
            .email(email)
            .businessNo(businessNo)
            .partnerName(partnerName)
            .build();
    }
}
