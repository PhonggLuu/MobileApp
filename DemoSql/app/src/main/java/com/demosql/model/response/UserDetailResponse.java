package com.demosql.model.response;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDetailResponse {
    private int id;
    private String userName;
    private String email;
    private LocalDateTime dob;
    private String address;
    private String phoneNumber;
    private String roleName;
    private String gender;
    private String imgUrl;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Integer ratingCount;
    private boolean isDelete;
    private boolean isVerify;
    private boolean status;
}

