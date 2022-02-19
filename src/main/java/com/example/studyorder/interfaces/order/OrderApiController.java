package com.example.studyorder.interfaces.order;

import com.example.studyorder.apllication.order.OrderFacade;
import com.example.studyorder.common.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderApiController {

    private final OrderFacade orderFacade;
    private final OrderDtoMapper orderDtoMapper;

    @PostMapping("/init")
    public CommonResponse registerOrder(@RequestBody @Valid OrderDto.RegisterOrderRequest request) {
        var orderCommand = orderDtoMapper.of(request);
        var orderToken = orderFacade.registerOrder(orderCommand);
        var response = orderDtoMapper.of(orderToken);
        return CommonResponse.success(response);
    }

    @GetMapping("/{orderToken}")
    public CommonResponse retrieveOrder(@PathVariable String orderToken) {
        var orderResult = orderFacade.retrieveOrder(orderToken);
        var response = orderDtoMapper.of(orderResult);
        return CommonResponse.success(response);
    }

    @PostMapping("/payment-order")
    public CommonResponse paymentOrder(@RequestBody @Valid OrderDto.PaymentRequest request) {
        var paymentCommand = orderDtoMapper.of(request);
        orderFacade.paymentOrder(paymentCommand);
        return CommonResponse.success("OK");
    }
}
