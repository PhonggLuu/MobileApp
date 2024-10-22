package com.demosql.model.response;

import java.io.Serializable;
import java.util.UUID;

public class CheckoutResponse implements Serializable {
    private UUID id;
    private int userId;
    private String userName;
    private double totalPrice;
    private Double shipPrice;
    private Double deposit;
    private String date;
    private boolean refundStatus;
    private int status;
    private int newStatus;
}
