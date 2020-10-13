package app.lyd.springbootproject.base.annotation;

import app.lyd.springbootproject.base.config.SwitchLocaleConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(SwitchLocaleConfiguration.class)
public @interface EnableLocaleSwitch {
}
