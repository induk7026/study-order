package co.kr.order.studyorder.doamin.partner.command;

import co.kr.order.studyorder.doamin.partner.Partner.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UpdatePartnerCommand {
    private final String partnerToken;
    private final Status status;
}
