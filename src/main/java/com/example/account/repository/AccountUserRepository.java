package com.example.account.repository;

import com.example.account.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountUserRepository
        extends JpaRepository<AccountUser, Long> {
    //<조회할 테이블(엔티티), pk의 타입>
}
