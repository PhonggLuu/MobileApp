package com.demosql.view;

import com.demosql.model.entities.Order;

import java.util.List;

public interface OrderManagementView {
    void showOrders(List<Order> orderList);
    void assignPageInfo(int totalPage);
}
