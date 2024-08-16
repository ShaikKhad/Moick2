package com.jsp.Crudoperationonetoonemappingrealation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
 Page<Bank> findAll(Pageable pageable);
}
