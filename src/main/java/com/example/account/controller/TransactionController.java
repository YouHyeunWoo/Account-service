package com.example.account.controller;

import com.example.account.aop.AccountLock;
import com.example.account.dto.CancelBalance;
import com.example.account.dto.QueryTransactionResponse;
import com.example.account.dto.UseBalance;
import com.example.account.exception.AccountException;
import com.example.account.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 잔액 관련 컨트롤러
 * 1.잔액 사용
 * 2.잔액 사용 취소
 * 3.거래 확인
 * Controller를 만드는데 Controller에서 필요로 하는 Service를 만드는데
 * 서비스에는 Repository가 필요로 하는데 레파지토리는 Entity가 필요로 한다 >엔티티를 만들어줌
 * 엔티티를 사용하는 레파지토리를 인터페이스 타입으로 만들어주고
 * 레파지토리를 사용하도록 서비스에 Injection해주고
 * 서비스에서 useBalance메소드를 만드는데 응답으로 Dto타입을 주기위해 entity의 쌍둥이인 Dto클래스를 만듬
 * Dto를 만드는 이유는 내가 원하는 정보만 내보낼 수 있도록 데이터를 삭제, 추가 할 수 있기 때문이다
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/transaction/use")
    @AccountLock
    public UseBalance.Response useBalance(
            @Valid @RequestBody UseBalance.Request request
    ) {
        try {
            return UseBalance.Response.from(transactionService.useBalance(request.getUserId(),
                    request.getAccountNumber(), request.getAmount()));
        } catch (AccountException e) {
            log.error("Failed to use balance. ");

            transactionService.saveFailedUseTransaction(
                    request.getAccountNumber(),
                    request.getAmount()
            );

            throw e;
        }
    }

    @PostMapping("/transaction/cancel")
    @AccountLock
    public CancelBalance.Response useBalance(
            @Valid @RequestBody CancelBalance.Request request
    ) {
        try {
            return CancelBalance.Response.from(transactionService.cancelBalance(request.getTransactionId(),
                    request.getAccountNumber(), request.getAmount()));
        } catch (AccountException e) {
            log.error("Failed to use balance. ");

            transactionService.saveFailedCancelTransaction(
                    request.getAccountNumber(),
                    request.getAmount()
            );

            throw e;
        }
    }

    @GetMapping("/transaction/{transactionId}")
    public QueryTransactionResponse queryTransaction(
            @PathVariable String transactionId) {
        return QueryTransactionResponse.from(
                transactionService.queryTransaction(transactionId));
    }
}
