package com.hp.util;

// 分页的工具类
public class PageBeanUtil {

    private int page; //第几页 --前端传过来的
    private int pagesize;// 每一页的条数 也是limit--前端传来的
    private int start; //索引  计算出来的 所以不添加到构造方法里

    public PageBeanUtil(int page, int pagesize) {
        this.page = page;
        this.pagesize = pagesize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }
    // 这里需要计算一下
    // 索引=(页数-1)*条数
    public int getStart() {
        return (page-1)*pagesize;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
