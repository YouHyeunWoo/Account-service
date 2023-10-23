package com.example.account.exception;

import com.example.account.type.ErrorCode;
import lombok.*;
import org.springframework.web.service.annotation.GetExchange;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountException extends RuntimeException{
    private ErrorCode errorCode;
    private String errorMessage;

    public AccountException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
