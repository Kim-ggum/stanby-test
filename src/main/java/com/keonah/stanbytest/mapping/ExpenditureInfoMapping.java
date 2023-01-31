package com.keonah.stanbytest.mapping;

import java.time.LocalDate;

public interface ExpenditureInfoMapping {
    String getPurpose();
    Long getAmount();
    LocalDate getDate();
}
