package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: AnnotationDemo
 * @Package annotation
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2018/3/22
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AnnotationDemo {

    enum  Status {START, END};

    Status status() default Status.START;

    boolean showSupport() default false;

    String name() default "";

    Class<?> testCase() default Void.class;

    Target target() default @Target(value = ElementType.METHOD);

    long[] values() default {0};

}
