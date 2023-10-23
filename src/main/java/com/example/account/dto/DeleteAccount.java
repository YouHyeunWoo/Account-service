package com.example.account.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

public class DeleteAccount {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request{ //Request할 때 Valid 하는 부분
        @NotNull //userId는 null 일 수 없다
        @Min(1) //userId는 0인 사람은 없다 >> 1부터 시작 >> userId는 1과 같거나 크다
        private Long userId;

        @NotBlank
        @Size(min = 10, max = 10)  //accountNumber은 10자리의 비어있지않은 문자
        private String accountNumber;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long userId;
        private String accountNumber;
        private LocalDateTime unRegisteredAt;

        //AccountDto클래스를 CreateAccount.Response클래스로 바꿔주는 메소드
        public static Response from(AccountDto accountDto){
            return Response.builder()
                    .userId(accountDto.getUserId())
                    .accountNumber(accountDto.getAccountNumber())
                    .unRegisteredAt(accountDto.getUnRegisteredAt())
                    .build();
        }
    }
}
