package com.lhp.util;

/**
 * @Description:
 * @author: lihp
 * @date: 2021/9/28 4:36 下午
 */

import java.util.List;

/**
 * 抽象出来的分页类
 */
public class PageUtil {

    private int currentPageNum; //当前要看哪一页，当前页

    private int pageSize = 10;//每页显示的条数，页面显示数据条数

    private int totalSize;//总记录条数，总行数

    private int startIndex;//查询开始记录的索引 limit ? ? 开始索引

    private int totalPageNum;//总页数

    private int prePageNum;//上一页

    private int nextPageNum;//下一页

    private List records;//当前页的记录集


    //用于显示页面上的导航的页号  用户可自定义
    //开始页码
    private int startPageNum;
    //结束页码
    private int endPageNum;

    private String url;

    //使用构造方法，传递必要的两个参数.第一个是页码，第二个总记录条数
    public PageUtil(int currentPageNum, int totalrecords, int pageSize) {
        this.pageSize = pageSize;
        this.currentPageNum = currentPageNum;
        this.totalSize = totalrecords;
        //计算开始记录索引
        this.startIndex = (currentPageNum - 1) * pageSize;
        //计算总页数
        this.totalPageNum = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;


        this.prePageNum = getPrePageNum1();

        this.nextPageNum = getNextPageNum1();
        //计算开始和结束页号  这个根据自身可设计
        if (totalPageNum > 9) {
            //如果总页数大于9 开始页面
            startPageNum = currentPageNum - 4;
            //结束页面
            endPageNum = currentPageNum + 4;

            if (startPageNum < 1) {
                startPageNum = 1;
                endPageNum = startPageNum + 8;
            }
            if (endPageNum > totalPageNum) {
                endPageNum = totalPageNum;
                startPageNum = endPageNum - 8;
            }

        } else {
            startPageNum = 1;
            endPageNum = totalPageNum;

        }


    }


    public int getStartPageNum() {
        return startPageNum;
    }

    public void setStartPageNum(int startPageNum) {
        this.startPageNum = startPageNum;
    }

    public int getEndPageNum() {
        return endPageNum;
    }

    public void setEndPageNum(int endPageNum) {
        this.endPageNum = endPageNum;
    }

    //得到上一页方法
    public int getPrePageNum1() {
        System.out.println("得到上一页方法");
        //上一页等于当前页减1
        prePageNum = currentPageNum - 1;
        //如过上一个小于0
        if (prePageNum <= 0) {    //上一页等于1
            System.out.println("上一页小于0");
            prePageNum = 1;
        }
        return prePageNum;
    }

    //得到下一页方法
    public int getNextPageNum1() {
        //下一页等于当前页加1
        System.out.println("得到下一页的方法");
        nextPageNum = currentPageNum + 1;
        //如果下一页大于总页数
        if (nextPageNum > totalPageNum) {    //下一页等于总页数
            System.out.println("下一页大于总页数");
            nextPageNum = totalPageNum;
        }
        return nextPageNum;
    }

    public int getPrePageNum() {
        return prePageNum;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public int getNextPageNum() {
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PageUtil [currentPageNum=" + currentPageNum + ", pageSize=" + pageSize + ", totalSize=" + totalSize
                + ", startIndex=" + startIndex + ", totalPageNum=" + totalPageNum + ", 上一页=" + prePageNum
                + ", 下一页=" + nextPageNum + ", records=" + records + ", startPageNum=" + startPageNum
                + ", endPageNum=" + endPageNum + ", url=" + url + "]";
    }


}
