package co.kr.order.studyorder.interfaces.partner;

import co.kr.order.studyorder.application.partner.PartnerFacade;
import co.kr.order.studyorder.common.response.CommonResponse;
import co.kr.order.studyorder.interfaces.partner.dto.PartnerDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/partners")
public class PartnerController {

    private final PartnerFacade partnerFacade;

    @PostMapping
    public CommonResponse registerPartner(@RequestBody @Valid PartnerDto.RegisterRequest request) {
        var partnerInfo = partnerFacade.registerPartner(request.toCommand());
        var response = new PartnerDto.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
    }

    @PutMapping("/{partnerToken}")
    public CommonResponse updatePartner(
        @PathVariable String partnerToken,
        @RequestBody @Valid PartnerDto.UpdateRequest request
    ){
        partnerFacade.updatePartner(request.toCommand(partnerToken));
        return CommonResponse.success(null);
    }
}
