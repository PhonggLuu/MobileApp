package com.demosql.view;

import com.demosql.model.entities.OrderDetail;

import java.util.List;

public interface OrderDetailView {
    void showOrderDetail(List<OrderDetail> orderDetailList);
}
