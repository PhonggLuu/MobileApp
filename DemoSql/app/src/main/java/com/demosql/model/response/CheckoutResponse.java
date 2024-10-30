package com.demosql.model.response;

import com.demosql.model.entities.OrderDetail;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class CheckoutResponse implements Serializable {
    private String id;
    private int userId;
    private String userUserName;
    private double totalPrice;
    private double shipPrice;
    private String deposit;
    private String date;
    private boolean refundStatus;
    private int status;
    private int newStatus;
    private List<OrderDetail> orderDetails;
}
