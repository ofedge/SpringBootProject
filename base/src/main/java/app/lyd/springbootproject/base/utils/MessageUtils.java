package app.lyd.springbootproject.base.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class MessageUtils {

    private static MessageSource messageSource;

    public static void setMessageSource(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    /**
     * if @EnableLocaleSwitch is used, the SwitchLocaleConfiguration will be loaded, and the messageSource will be set
     *
     * @return whether @EnableLocaleSwitch is used
     */
    public static boolean isLocaleSwitchEnable() {
        return messageSource != null;
    }

    public static String getMessage(String code, Object ...params) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, params, locale);
    }

    public static String getErrorMsg(int code, Object ...params) {
        return getMessage("error." + code, params);
    }

    /**
     *
     * @param code
     * @param params
     * @return if @EnableLocaleSwitch is used, the i18n code message is returned, otherwise the code itself will be return
     */
    public static String getMessageOrDefaultSelf(String code, Object ...params) {
        return isLocaleSwitchEnable() ? getMessage(code, params) : code;
    }
}
