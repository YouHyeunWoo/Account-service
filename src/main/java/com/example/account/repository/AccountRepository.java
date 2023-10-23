package com.example.account.repository;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Bean으로 등록하기위해 Repository라는 어노테이션을 붙힘
public interface AccountRepository extends JpaRepository<Account, Long> {
    //Account테이블에 접속하기 위한 인터페이스   JpaRepository는 2가지의 타입을 받는데
    //하나는 Repository가 활용하게 될 엔티티이고, 두번째는 이 엔티티의 프라이머리키의 타입이다

    Optional<Account> findFirstByOrderByIdDesc(); //맨 처음 값을 가져오는데 정렬순서를 Id로 하고
                                                    //그 것을 Desc(역순)으로 가져온다

    Integer countByAccountUser(AccountUser accountUser);

    Optional<Account> findByAccountNumber(String AccountNumber);

    List<Account> findByAccountUser(AccountUser accountUser);


}
