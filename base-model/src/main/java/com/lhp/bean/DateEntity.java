package com.lhp.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * @Description:
 * @author: lihp
 * @date: 2021/11/8 16:11
 */
@Data
@EqualsAndHashCode()
@Accessors(chain = true)
public class DateEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 保养日期
     */
    private LocalDate upholdDate;

    /**
     * 实际保养人
     */
    private String upholdPerson;

}
