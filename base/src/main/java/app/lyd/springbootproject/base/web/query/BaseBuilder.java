package app.lyd.springbootproject.base.web.query;

public abstract class BaseBuilder<T extends BaseQuery> {

    private T query;

    protected BaseBuilder(T query) {
        this.query = query;
    }

    protected void setPage(int page, int size) {
        query.setPage(page);
        query.setSize(size);
    }


}
