package com.keonah.stanbytest.service;

import com.keonah.stanbytest.mapping.MoneyInfoMapping;
import com.keonah.stanbytest.dto.MoneyInputDTO;
import com.keonah.stanbytest.dto.MoneyListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MoneyService {
    void inputMoney(MoneyInputDTO moneyInputDto);

    Page<MoneyInfoMapping> getMoneyList(MoneyListDTO moneyListDto, Pageable pageable);

    Long getMoney();

}
