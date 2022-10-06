package com.oasis;

import com.oasis.common.util.DateUtils;
import com.oasis.data.entity.Member;
import com.oasis.data.entity.Statistics;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsTest {

    @Test
    public void test() {
        LocalDate targetMonth = DateUtils.getFirstDayOfMonth(LocalDate.now());
        List<Member> members = getTestMember();
        int workingDays = DateUtils.countByWorkingDay(targetMonth);
        List<Statistics> initStatistics = members.stream()
                .map(m -> {
                    Statistics s = Statistics.builder()
                            .userSid(m.getSid())
                            .date(targetMonth)
                            .workingDayCount(workingDays)
                            .officeWorkCount(workingDays)
                            .build();
                    s = calculateWorkingRate(s);
                    return s;
                })
                .collect(Collectors.toList());

        System.out.println(initStatistics);
    }

    public Statistics calculateWorkingRate(Statistics s){
        long workingDay = s.getWorkingDayCount();
        long officeWorkCount = s.getOfficeWorkCount();
        long remoteWorkCount = s.getRemoteWorkCount();
        long dayOffCount = s.getDayOffCount();
        long halfDayOffCount = s.getHalfDayOffCount();

        s.setOfficeWorkRate(((double)officeWorkCount)/workingDay * 100);
        s.setRemoteWorkRate(((double)remoteWorkCount)/workingDay * 100);
        s.setDayOffRate(((double)dayOffCount)/workingDay * 100);

        return s;
    }

    public List<Member> getTestMember() {
        List<Member> ret = new ArrayList<>();
        ret.add(Member.builder().sid(0L).build());
        ret.add(Member.builder().sid(1L).build());
        ret.add(Member.builder().sid(2L).build());

        return ret;
    }
}
