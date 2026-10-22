package colesico.zacepco.scriptpack.yaml;

import java.lang.annotation.*;

/**
 * Multiline comment
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface YamlComment {
    String[] text();
    boolean oneTime() default true;
    boolean topBlank() default true;
    boolean inline() default false;
}