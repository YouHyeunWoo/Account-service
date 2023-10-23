package com.example.account.domain;

import com.example.account.exception.AccountException;
import com.example.account.type.AccountStatus;
import com.example.account.type.ErrorCode;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor  //
@AllArgsConstructor //빌더로 객체를 생성하고 사용할때는 이 2개가 필요하다
@Builder
@Entity//엔티티란 일종의 설정 클래스이다
@EntityListeners(AuditingEntityListener.class)
//이 클래스는 클래스이지만 사실은 데이터베이스의 테이블을 만드는것이다.
public class Account {
    @Id //Account라는 테이블에 id를 프라이머리키로 설정해주는 어노테이션
    @GeneratedValue
    private Long id;

    @ManyToOne
    private AccountUser accountUser;

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private Long balance;

    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    //계좌의 잔액을 처리하는 로직은 엔티티 안에서 처리해주는 것이 안전한 방법이다
    public void useBalance(Long amount){
        if(amount > balance){
            throw new AccountException(ErrorCode.AMOUNT_EXCEED_BALANCE);
        }
        balance -= amount;
    }

    public void cancelBalance(Long amount){
        if(amount < 0){
            throw new AccountException(ErrorCode.INVALID_REQUEST);
        }
        balance += amount;
    }
}
