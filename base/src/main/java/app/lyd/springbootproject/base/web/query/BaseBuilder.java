package app.lyd.springbootproject.base.web.query;

import org.springframework.util.StringUtils;

import java.util.Map;

public abstract class BaseBuilder<T extends BaseQuery> {

    protected T query;

    protected BaseBuilder(T query) {
        this.query = query;
    }

    protected void setPage(int page, int size) {
        query.setPage(page);
        query.setSize(size);
    }

    public T build() {
        return query;
    }

    // return default order field, the field must be a key of orderMap()
    protected abstract String defaultOrder();

    // return orderMap, key is a string(provided by request), and the value is a valid sql order by statement
    protected abstract Map<String, String> orderMap();

    public abstract <E extends BaseBuilder> E order(String order);

    // order is a string divided by ":", eg: id:1, 1: asc, 0: desc
    protected void setOrder(String order) {
        if (StringUtils.isEmpty(order) || order.split(":").length != 2) {
            query.setOrder(orderMap().get(defaultOrder()) + " asc");
            return;
        }
        String[] orders = order.split(":");
        StringBuilder orderBuilder = new StringBuilder();
        orderBuilder.append(orderMap().get(orders[0]));
        orderBuilder.append(" ");
        orderBuilder.append("1".equals(orders[1]) ? "asc" : "desc");
        query.setOrder(orderBuilder.toString());
    }

}
