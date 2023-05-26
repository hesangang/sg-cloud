package com.sangang.common.base.validation.annotaion;

import com.sangang.common.base.validation.validator.NotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 非空
 *
 * @author sangang
 * @create 2019-01-25
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {NotNullValidator.class})
public @interface NotNull {
    /**
     * 错误消息.
     *
     * @return 错误消息.
     */
    String message() default "参数长度错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
