package com.example.account.dto;

import com.example.account.type.TransactionResultType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * {
 *     "userId":1,
 *     "accountNumber":"1000000000",
 *     "amount":1000
 * }
 */

public class UseBalance {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request{ //Request할 때 Valid 하는 부분
        @NotNull //userId는 null 일 수 없다
        @Min(1) //userId는 0인 사람은 없다 >> 1부터 시작
        private Long userId;

        @NotBlank
        @Size(min = 10, max = 10)
        private String accountNumber;

        @NotNull
        @Min(10)
        @Max(1000_000_000)
        private Long amount;
    }

    /**
     * {
     *     "accountNumber":"1234567890",
     *     "transactionResult":"S",
     *     "transactionId":"c2033bb6d82a4250aecf8e27c49b63",
     *     "amount":1000,
     *     "transactedAt":"2202-06-01T23:26:14.671859"  //거래시간
     * }
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private String accountNumber;
        private TransactionResultType transactionResultType;
        private String transactionId;
        private Long amount;
        private LocalDateTime transactedAt;

        public static Response from(TransactionDto transactionDto) {
            return Response.builder()
                    .accountNumber(transactionDto.getAccountNumber())
                    .transactionResultType(transactionDto.getTransactionResultType())
                    .transactionId(transactionDto.getTransactionId())
                    .amount(transactionDto.getAmount())
                    .transactedAt(transactionDto.getTransactedAt())
                    .build();
        }
    }
}
