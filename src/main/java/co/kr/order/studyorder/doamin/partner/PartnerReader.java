package co.kr.order.studyorder.doamin.partner;

public interface PartnerReader {

    Partner getPartner(Long partnerId);
    Partner getPartner(String partnerToken);
}
