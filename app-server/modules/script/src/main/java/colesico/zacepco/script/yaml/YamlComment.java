package colesico.zacepco.script.yaml;

import java.lang.annotation.*;

/**
 * Multiline comment
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface YamlComment {
    String[] text();

    String field() default "";

    boolean oneTime() default true;

    boolean topBlank() default true;

    boolean inline() default false;
}