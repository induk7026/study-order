package com.example.studyorder.domain.order;

public interface OrderReader {
    Order getOrder(String orderToken);
}
