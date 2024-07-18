package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
public @interface NoDocumented {

    /**
     * solve this message
     */
    String value() default "test NO Document";
}
