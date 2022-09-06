package com.oasis.common.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * TODO ::
 * Entity에 대한 Email validator는 이미 존재함.(null 체크는 별도로 필요/javax.validation.constraints.Email) 
 * 추후 필요한 다른 validator에 참조하기 위해 코드 유지. 
 * */
public class EmailValidator implements ConstraintValidator<Email,String> {
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO :: 최소길이 제한?
        return value != null && value.matches("^(.+)@(.+)$") && (value.length() > 5);
    }
    
}
