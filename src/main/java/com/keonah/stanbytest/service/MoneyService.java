package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.MoneyInputDto;
import com.keonah.stanbytest.dto.MoneyListDto;
import com.keonah.stanbytest.entity.MoneyEntity;

import java.util.List;

public interface MoneyService {
    void inputMoney(MoneyInputDto moneyInputDto);

    List<MoneyEntity> getMoneyList(MoneyListDto moneyListDto);

    Long getMoney();
}
