package com.keonah.stanbytest.mapping;

import java.time.LocalDate;

public interface ExpenditureInfoMapping {

    // 회비 사용내역을 조회할 때 원하는 데이터만 얻기 위함
   String getPurpose();
    Long getAmount();
    LocalDate getDate();
}
