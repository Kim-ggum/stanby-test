package com.keonah.stanbytest.mapping;

import java.time.LocalDate;

public interface MoneyInfoMapping {

    // 입금 내역을 조회할 때 원하는 데이터만 얻기 위함
    String getMember();
    Long getAmount();
    LocalDate getCreatedDate();

}
