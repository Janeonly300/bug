package com.atjianyi.domain;

import java.util.List;

public class PageBean<T> {
    private int totalCount; //总的记录数
    private int totalPage; // 总页数
    private List<T> list ; // 每一页当中的数据
    private int currentPage; //当前的页码
    private int rows ; // 每页显示多少条数据

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

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
