package app.lyd.springbootproject.base.config;

import app.lyd.springbootproject.base.resolver.SpbLocaleResolver;
import app.lyd.springbootproject.base.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class SwitchLocaleConfiguration {

    public SwitchLocaleConfiguration(MessageSource messageSource) {
        MessageUtils.setMessageSource(messageSource);
    }

    private static final Logger logger = LoggerFactory.getLogger(SwitchLocaleConfiguration.class);

    @Bean("localeResolver")
    @ConditionalOnMissingBean(name = "localeResolver")
    public LocaleResolver spbLocaleResolver() {
        logger.info("SpbLocaleResolver initialized!");
        return new SpbLocaleResolver();
    }
}
