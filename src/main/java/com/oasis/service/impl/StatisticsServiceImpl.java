package com.oasis.service.impl;

import com.oasis.common.util.DateUtils;
import com.oasis.data.dto.MemberDto;
import com.oasis.data.dto.response.StatisticsMonthlyResponse;
import com.oasis.data.entity.Member;
import com.oasis.data.entity.Statistics;
import com.oasis.repository.StatisticsRepository;
import com.oasis.service.MemberService;
import com.oasis.service.ScheduleService;
import com.oasis.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final ScheduleService scheduleService;
    private final MemberService memberService;


    public List<StatisticsMonthlyResponse> monthly(int year) {
        StatisticsMonthlyResponse[] monthly = new StatisticsMonthlyResponse[12];

        for(int i = 0; i < monthly.length; i++) {
            monthly[i] = new StatisticsMonthlyResponse();
            monthly[i].setYear(year);
            monthly[i].setMonth( i + 1 );
        }

        LocalDate firstDayOfYear = DateUtils.firstDayOfYear(year);
        LocalDate lastDayOfYear = DateUtils.lastDayOfYear(year);

        List<Statistics> allStatistics = statisticsRepository.findByDateBetween(firstDayOfYear, lastDayOfYear);

        allStatistics.stream().forEach(s -> {
            int month = s.getDate().getMonth().getValue();
            monthly[month].setWorkingDayCount( monthly[month].getWorkingDayCount() + s.getWorkingDayCount() );
            monthly[month].setOfficeWorkCount( monthly[month].getOfficeWorkCount() + s.getOfficeWorkCount() );
            monthly[month].setRemoteWorkCount( monthly[month].getRemoteWorkCount() + s.getRemoteWorkCount() );
            monthly[month].setDayOffCount( monthly[month].getDayOffCount() + s.getDayOffCount() );
            monthly[month].setHalfDayOffCount( monthly[month].getHalfDayOffCount() + s.getHalfDayOffCount() );
        });

        return Arrays.asList(monthly);
    }

    private void aggregateScheduleByMemberSid(Long memberSid) {
    }

    public void aggregateSchedule() {

        LocalDate today = LocalDate.now();
        LocalDate startDate = DateUtils.getFirstDayOfMonth(today); // ????????? ??????
        LocalDate endDate = DateUtils.getLastDayOfMonth(today);  // ????????? ????????????
        List<Statistics> existSchedules = statisticsRepository.findByDateBetween(startDate, endDate); // ?????? ?????? ?????? ????????? ?????????

        //scheduleService.getScheduleByUserSid()

        // ?????? ???????????? ?????????
        //List<MemberDto> members = memberService.getAllMembers();

        // ?????? ???????????? ?????? ??????
    }

    /**
     * ?????? ??????????????? ?????? ?????? ???????????? ????????? ?????????.
     * @param targetMonth
     */
    public void doFirstDayOfMonth(LocalDate targetMonth) {
        List<MemberDto> members = memberService.getAllMembers();
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

    private Statistics calculateWorkingRate(Statistics s){
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
