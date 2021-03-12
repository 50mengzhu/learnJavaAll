package me.mica.learn.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {

    String value() default "";

    public enum Color {
        BLUE,
        RED,
        GREEN
    }

    Color color() default Color.BLUE;
}
