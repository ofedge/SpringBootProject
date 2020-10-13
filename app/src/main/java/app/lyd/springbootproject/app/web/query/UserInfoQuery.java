package app.lyd.springbootproject.app.web.query;

import app.lyd.springbootproject.base.web.query.BaseBuilder;
import app.lyd.springbootproject.base.web.query.BaseQuery;

import java.util.HashMap;
import java.util.Map;

public class UserInfoQuery extends BaseQuery {

    private String username;
    private String name;
    private String email;
    private String active;
    private String createTimeStart;
    private String createTimeEnd;
    private String lastModifyStart;
    private String lastModifyEnd;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseBuilder<UserInfoQuery> {

        protected Builder() {
            super(new UserInfoQuery());
        }

        public Builder username(String username) {
            query.username = username;
            return this;
        }

        public Builder name(String name) {
            query.name = name;
            return this;
        }

        public Builder email(String email) {
            query.email = email;
            return this;
        }

        public Builder active(String active) {
            query.active = active;
            return this;
        }

        public Builder createTimeStart(String createTimeStart) {
            query.createTimeStart = createTimeStart;
            return this;
        }

        public Builder createTimeEnd(String createTimeEnd) {
            query.createTimeEnd = createTimeEnd;
            return this;
        }

        public Builder lastModifyStart(String lastModifyStart) {
            query.lastModifyStart = lastModifyStart;
            return this;
        }

        public Builder lastModifyEnd(String lastModifyEnd) {
            query.lastModifyEnd = lastModifyEnd;
            return this;
        }

        public Builder page(Integer page, Integer size) {
            super.setPage(page, size);
            return this;
        }

        @Override
        public Builder order(String order) {
            super.setOrder(order);
            return this;
        }

        @Override
        public String defaultOrder() {
            return "id";
        }

        @Override
        protected Map<String, String> orderMap() {
            return new HashMap(){{
                put("id", "id");
                put("name", "name");
                put("email", "email");
                put("active", "active");
                put("createTime", "create_time");
                put("lastModify", "last_modify");
            }};
        }
    }
}
