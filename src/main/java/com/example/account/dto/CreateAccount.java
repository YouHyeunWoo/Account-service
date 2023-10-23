package com.example.account.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

public class CreateAccount {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request{ //Request할 때 Valid 하는 부분
        @NotNull //userId는 null 일 수 없다
        @Min(1) //userId는 0인 사람은 없다 >> 1부터 시작
        private Long userId;

        @NotNull
        @Min(0)
        private Long initialBalance;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long userId;
        private String accountNumber;
        private LocalDateTime registeredAt;

        //AccountDto클래스를 CreateAccount.Response클래스로 바꿔주는 메소드
        public static Response from(AccountDto accountDto){
            return Response.builder()
                    .userId(accountDto.getUserId())
                    .accountNumber(accountDto.getAccountNumber())
                    .registeredAt(accountDto.getRegisteredAt())
                    .build();
        }
    }
}
