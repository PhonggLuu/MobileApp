package com.demosql.view;

import com.demosql.model.entities.Order;
import com.demosql.model.response.UserSignUpResponse;

import java.util.List;

public interface UserManagementView {
    void showUsers(List<UserSignUpResponse> userList);
    void assignPageInfo(int totalPage);
}
