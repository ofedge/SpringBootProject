package app.lyd.springbootproject.dao.util;

import app.lyd.springbootproject.base.web.query.BaseQuery;
import app.lyd.springbootproject.base.web.result.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.Function;
import java.util.stream.Collectors;

public class PageUtil {

    public static void startPageAndOrder(BaseQuery query) {
        int page = query.getPage() != null ? query.getPage() : 1;
        int size = query.getSize() != null ? query.getSize() : 5;
        PageHelper.startPage(page, size);
        PageHelper.orderBy(query.getOrder());
    }

    public static <T, R> PageResult<R> parseResult(Page<T> page, Function<T, @Nullable R> converter) {
        if (page.getPageNum() > 0) {
            return new PageResult<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(),
                    page.getResult().stream().map(converter).collect(Collectors.toList()));
        } else {
            return new PageResult(page.getResult().stream().map(converter).collect(Collectors.toList()));
        }
    }
}
