package com.project.vn.dto;

import lombok.Data;


@Data
public class Employee {
    private Integer id;
    private String fullName;
    private String address;
    private String phone;
    private String gender;
    private String position;
    private String department;
    private Boolean isDeleted;
}
