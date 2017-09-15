package com.utils;

/**
 * @Author 王岚枫
 * @Datetime 2017年08月19日 15:03
 */
public class Page {
    private Integer pageSize = 0;
    private Integer pageNum = 0;
    private Long pageTotal = 0l;
    private Integer pageStar = 0;
    private Boolean isDesc;
    private Boolean isPage;
    private Integer pageAll = 1;


    public Integer getPageAll() {
        return pageAll;
    }

    public void setPageAll(Integer pageAll) {
        this.pageAll = pageAll;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Long pageTotal) {
        this.pageTotal = pageTotal;
        this.pageAll = pageSize != 0 ? (int) (this.pageTotal / pageSize + (this.pageTotal % pageSize > 0 ? 1 : 0)) : 1;
    }

    public Integer getPageStar() {
        return pageStar;
    }

    public void setPageStar(Integer pageStar) {
        this.pageStar = pageStar;
    }

    public Boolean getDesc() {
        return isDesc;
    }

    public void setDesc(Boolean desc) {
        isDesc = desc;
    }

    public Boolean getPage() {
        return isPage;
    }

    public void setPage(Boolean page) {
        isPage = page;
    }

    public Page() {
    }

    public Page(Boolean isPage) {
        this.isPage = false;
    }

    public Page(Integer pageSize, Integer pageNum) {
        this(pageSize, pageNum, false);
    }

    public Page(Integer pageSize, Integer pageNum, Boolean isDesc) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.pageStar = pageSize * (pageNum - 1);
        this.isDesc = isDesc;
        this.isPage = true;
    }
}
