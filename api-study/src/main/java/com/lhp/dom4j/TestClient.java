package com.lhp.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * @Description:
 * @author: lihp
 * @date: 2021/12/24 14:57
 */
public class TestClient {

    public static void main(String[] args) throws DocumentException {

        // 通过读取文件的方式获取xml
        SAXReader reader = new SAXReader();

        Document document = reader.read(new File("files/aa.html"));

        Element rootElement = document.getRootElement();
        System.out.println("rootElement = " + rootElement);
    }
}
