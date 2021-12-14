package com.lhp.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @Description:
 * @author: lihp
 * @date: 2021/9/28 4:41 下午
 */
public class testClient {

    @Test
    public void test() {
        PageUtil pageUtil = new PageUtil(2, 30, 2);
        pageUtil.setPageSize(2);
        int startIndex = pageUtil.getStartIndex();
        System.out.println("startIndex = " + startIndex);
    }

    /**
     *
      */
    @Test
        public void test66(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","AMumu");
        jsonObject.put("age","20");
        jsonObject.put("sex","male");
        String s = jsonObject.toString();
        System.out.println("s = " + s);
    }
}
