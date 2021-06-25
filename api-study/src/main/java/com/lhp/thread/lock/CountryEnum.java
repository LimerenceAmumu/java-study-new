package com.lhp.thread.lock;

import java.util.Objects;

/**
 * @author Amumu
 * @create 2019/9/24 15:27
 */
public enum CountryEnum {
    ONE(1,"one"),TWO(2,"two"),THREE(3,"three"),FOUR(4,"four"),;
    private Integer code;
    private String name;
    public static CountryEnum foreach(Integer index){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum element : values) {
            if(Objects.equals(index,element.getCode())){
                return element;
            }
        }
        return null;
    }

    CountryEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
