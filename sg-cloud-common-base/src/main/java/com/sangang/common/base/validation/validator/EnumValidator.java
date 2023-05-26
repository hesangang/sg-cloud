package com.sangang.common.base.validation.validator;


import com.sangang.common.base.validation.annotaion.EnumValue;
import com.sangang.common.base.validation.enums.IEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 枚举校验.
 *
 * @author sangang
 */
public class EnumValidator implements ConstraintValidator<EnumValue, Object> {

    private Class<? extends IEnum<Object>> enumClass;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (null != value) {
            return null != IEnum.parseByCode(value, enumClass);
        }
        return true;
    }
}
