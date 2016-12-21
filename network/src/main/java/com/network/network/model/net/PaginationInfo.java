package com.network.network.model.net;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author woniu
 * @title Pagination
 * @description 分页信息实体类
 * @modifier
 * @date
 * @since 2015/6/30 13:48
 **/
public class PaginationInfo implements Serializable {

    public static final int DEFAULT_PAGE_NO = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int DEFAULT_PAGE_COUNT = 20;

    /**
     * 当前页序
     */
    @SerializedName("pageno")
    public int pageNo;
    /**
     * 每页显示条目数量
     */
    @SerializedName("pagesize")
    public int pageSize;
    /**
     * 总条目数
     */
    @SerializedName("totalcount")
    public int totalCount;
    /**
     * 排序字段名
     */
    @SerializedName("orderby")
    public String orderBy;
    /**
     * 是否升序
     */
    @SerializedName("asc")
    public boolean asc = false;

    public PaginationInfo() {
        pageNo = DEFAULT_PAGE_NO;     //当前页序
        pageSize = DEFAULT_PAGE_SIZE;   //每页显示条目数量
        totalCount = DEFAULT_PAGE_COUNT;   //总条目数
    }

    public PaginationInfo(int pageNo, int pagesize) {
        this.pageNo = pageNo;
        this.pageSize = pagesize;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public int getTolalPages() {
        return (totalCount + pageSize - 1) / pageSize;
    }

    /**
     * 上一页
     *
     * @return
     */
    public int getPreviousPageNo() {
        if (pageNo <= 1) {
            return 1;
        }
        return pageNo - 1;
    }

    /**
     * 下一页
     *
     * @return
     */
    public int getNextPageNo() {
        if (pageNo >= getLastPage()) {
            return getLastPage();
        }
        return pageNo + 1;
    }

    /**
     * 上一页
     *
     * @return
     */
    public void toPreviousPageNo() {
        if (pageNo <= 1) {
            pageNo = 1;
        } else {
            pageNo--;
        }
    }

    /**
     * 下一页
     *
     * @return
     */
    public void toNextPageNo() {
        if (pageNo >= getLastPage()) {
            pageNo = getLastPage();
        } else {
            pageNo++;
        }
    }

    /**
     * 获取首页
     *
     * @return
     */
    public int getFirstPage() {
        return 1;
    }

    /**
     * 获取尾页
     *
     * @return
     */
    public int getLastPage() {
        return getTolalPages();
    }
}
