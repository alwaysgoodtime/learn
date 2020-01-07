package cn.itcast.travel.domain;

import java.util.List;

/**封装分类商品页面
 * @author goodtime
 * @create 2020-01-05 5:24 下午
 */
public class PageBean<T> {
    private int currentPages;
    private int allRows;
    private int pageCount;
    private List<T> list;

    public PageBean() {
    }

    public int getCurrentPages() {
        return currentPages;
    }

    public void setCurrentPages(int currentPages) {
        this.currentPages = currentPages;
    }

    public int getAllRows() {
        return allRows;
    }

    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
