package com.syd.util;

import java.util.List;

public class Page<T> {
    private int pageIndex;//当前页
    private int pageSize;//每页条数
    private int totalCount;//符合查询条件的总记录数

    //需要计算
    private int totalPage;//总页数
    private int startRow;//数据库起始记录指针

    private List<T> list;//符合查询条件的列表_注意这里是泛型

    //分页显示的页数,比如在页面上显示1，2，3，4，5页，start就为1，end就为5，这个也是算过来的
    private int start;
    private int end;

    //构造方法


    public Page(int pageIndex, int pageSize, int totalCount) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;

        //totalPage 总页数
        if (totalCount % pageSize == 0) {
            //说明整除，正好每页显示pageSize条数据，没有多余一页要显示少于pageSize条数据的
            this.totalPage = totalCount / pageSize;
        } else {
            //不整除，就要在加一页，来显示多余的数据。
            this.totalPage = totalCount / pageSize + 1;
        }
        //数据库中的开始索引
        this.startRow = (pageIndex - 1) * pageSize;

        //显示5页，这里自己可以设置，想显示几页就自己通过下面算法修改
        this.start = 1;
        this.end = 5;
        //显示页数的算法
        if(totalPage <=5){
            //总页数都小于5，那么end就为总页数的值了。
            this.end = this.totalPage;
        }else{//totalPage>5
            //总页数大于5，那么就要根据当前是第几页，来判断start和end为多少了，
            this.start = pageIndex - 2;
            this.end = pageIndex + 2;
            if(start <= 0){
                //比如当前页是第1页，或者第2页，那么就不遵循上面这个规则，
                this.start = 1;
                this.end = 5;
            }
            if(end > this.totalPage){
                //比如当前页是倒数第2页或者最后一页，也同样不符合上面这个规则
                this.end = totalPage;
                this.start = end - 4;
            }
        }
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}