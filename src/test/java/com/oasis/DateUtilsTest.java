package com.oasis;

import com.oasis.common.util.DateUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilsTest {

    @Test
    public void 날짜계산_test() {
        LocalDate date = LocalDate.now();

        LocalDate start = DateUtils.getFirstDayOfMonth(date);
        LocalDate end = DateUtils.getLastDayOfMonth(date);

        System.out.println(end);
        int remainDay = DateUtils.remainDaysOfMonth(start, end);

        assertThat(remainDay + 1).isEqualTo(date.lengthOfMonth());

        System.out.println(DateUtils.countByWorkingDay(date));
    }

}
