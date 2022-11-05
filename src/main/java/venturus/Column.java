package venturus;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface Column {
    int index() default 0;

    String name() default "";

    boolean required() default true;

    /**
     * Regex validation
     */
    String validation() default "";
}
