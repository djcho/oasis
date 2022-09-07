package com.oasis.common.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * TODO :: 
 * Entity에 대한 Email validator는 이미 존재함.(null 체크는 별도로 필요/javax.validation.constraints.Email) 
 * 추후 필요한 다른 validator에 참조하기 위해 코드 유지. 
 * */
@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
    
    // TODO :: message 표준 형식 정의 필요(?)
    String message() default "Invalid email";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
}
