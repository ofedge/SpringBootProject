package app.lyd.springbootproject.base.web.result;

import java.util.List;

public class PageResult<T> {
    private int page;
    private int size;
    private int pages;
    private long total;
    private List<T> values;

    public PageResult(int page, int size, int pages, long total, List<T> values) {
        this.page = page;
        this.size = size;
        this.pages = pages;
        this.total = total;
        this.values = values;
    }

    public PageResult(List<T> values) {
        this.values = values;
        this.total = values != null ? values.size() : 0L;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}
