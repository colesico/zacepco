package colesico.zacepco.script.yaml;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface YamlComment {
    String value();
}