package com.lhp.bean;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/4/25 17:17
 */
@Data
public class UserDetail {
    private String name;
    private Integer age;
    private LocalDateTime createTime;
    private LocalDate createDate;
    private String createDateStr;
    private String address;
    private String email;
    private String tel;
    private String host;
}
