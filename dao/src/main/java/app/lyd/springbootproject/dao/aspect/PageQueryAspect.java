package app.lyd.springbootproject.dao.aspect;

import app.lyd.springbootproject.base.web.query.BaseQuery;
import app.lyd.springbootproject.dao.annotation.PageQuery;
import app.lyd.springbootproject.dao.util.PageUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PageQueryAspect {

    @Pointcut("@annotation(pageQuery)")
    public void applyPageQuery(PageQuery pageQuery){
    }

    @Around(value = "applyPageQuery(pageQuery)", argNames = "joinPoint,pageQuery")
    public Object aroundMethod(ProceedingJoinPoint joinPoint, PageQuery pageQuery) throws Throwable {
        BaseQuery query = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof BaseQuery) {
                query = (BaseQuery) arg;
                break;
            }
        }
        if (query != null) {
            PageUtil.startPageAndOrder(query);
        }

        return joinPoint.proceed();
    }
}
