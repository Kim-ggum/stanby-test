package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.MoneyInputDto;
import com.keonah.stanbytest.dto.MoneyListDto;
import com.keonah.stanbytest.entity.MoneyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MoneyService {
    void inputMoney(MoneyInputDto moneyInputDto);

    Page<MoneyEntity> getMoneyList(MoneyListDto moneyListDto, Pageable pageable);

    Long getMoney();
}
