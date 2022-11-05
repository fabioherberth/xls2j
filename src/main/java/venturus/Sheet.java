package venturus;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface Sheet {
    String value();

    boolean validate() default true;

    /**
     * By default start at line 1 (skip header line)
     */
    int startOn() default 1;
}
