package org.poverty.povertyinformation.pobj;

import lombok.Data;

@Data
public class RecipientStu {

    private String school;
    private String name;
    private String idCard;
    private String address;
    private String helpedProject;//v受助项目
    private String helpedmoney;//受助金额
    private String helpedMessage;//受助信息
}
