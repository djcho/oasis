package com.oasis.service.impl;

import com.oasis.common.util.DateUtils;
import com.oasis.data.entity.Member;
import com.oasis.data.entity.MutableStatistics;
import com.oasis.data.entity.Statistics;
import com.oasis.repository.ImmutableStatisticsRepository;
import com.oasis.repository.MutableStatisticsRepository;
import com.oasis.repository.StatisticsRepository;
import com.oasis.service.MemberService;
import com.oasis.service.ScheduleService;
import com.oasis.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final ScheduleService scheduleService;
    private final MemberService memberService;
    private final MutableStatisticsRepository mutableStatisticsRepository;
    private final ImmutableStatisticsRepository immutableStatisticsRepository;

    private void aggregateScheduleByMemberSid(Long memberSid) {
    }

    public void aggregateSchedule() {

        LocalDate today = LocalDate.now();
        LocalDate startDate = DateUtils.getFirstDayOfMonth(today); // 이번달 첫날
        LocalDate endDate = DateUtils.getLastDayOfMonth(today);  // 이번달 마지막날
        List<Statistics> existSchedules = statisticsRepository.findByDateBetween(startDate, endDate); // 해당 달의 모든 정보를 가져옴

        //scheduleService.getScheduleByUserSid()

        // 멤버 리스트를 불러옴
        // 멤버 리스트와 멤버 스케
    }

    /**
     * 매달 시작할때는 현재 모든 직원들은 회사로 출근함.
     * @param targetMonth
     */
    public void doFirstDayOfMonth(LocalDate targetMonth) {
        List<Member> members = memberService.getAllMembers();
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

        statisticsRepository.saveAll(initStatistics);
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
}
