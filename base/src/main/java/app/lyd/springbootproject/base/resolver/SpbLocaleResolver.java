package app.lyd.springbootproject.base.resolver;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

public class SpbLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // find locale setting from query parameter
        String parameterLocale = request.getParameter("locale");
        if (!StringUtils.isEmpty(parameterLocale)) {
            String[] locales = parameterLocale.split("_");
            return locales.length > 1 ? new Locale(locales[0], locales[1]) : new Locale(locales[0]);
        }
        // find from request header
        return Optional.ofNullable(request.getLocale()).orElse(new Locale("en", "US"));
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        throw new UnsupportedOperationException("Please switch locale with browser setting or query param!");
    }
}
