package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
public @interface DocumentAnno {

     /**
      * solve this message
      */
     String value() default "test Document";
}
