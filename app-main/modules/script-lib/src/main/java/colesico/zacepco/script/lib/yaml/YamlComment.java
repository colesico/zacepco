package colesico.zacepco.script.lib.yaml;

import java.lang.annotation.*;

/**
 * Multiline comment
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface YamlComment {
    String[] text();
    boolean oneTime() default true;
}