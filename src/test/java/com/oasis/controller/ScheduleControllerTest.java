package com.oasis.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oasis.config.gson.LocalDateSerializer;
import com.oasis.config.gson.LocalDateTimeSerializer;
import com.oasis.controller.rest.ScheduleController;
import com.oasis.data.dto.request.ScheduleRequest;
import com.oasis.data.dto.response.ScheduleResponse;
import com.oasis.service.impl.ScheduleServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller 의 테스트는 단순히 입출력에 대한 모양새(프로토콜)만을 검증한다.
 * 데이터 혹은 기능의 동작에 대한 검증은 Service 테스트에서 수행한다.
 */
@WebMvcTest(ScheduleController.class)
public class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean //Service 객체는 controller 테스트에서는 외부 객체이므로 Mock객체를 사용한다.
    ScheduleServiceImpl scheduleService;

    final LocalDateTime createdTime = LocalDateTime.of(2022, 10, 1, 15, 25, 00);
    final LocalDateTime updatedTime = LocalDateTime.of(2022, 10, 2, 16, 35, 00);

    @Test
    @DisplayName("스케줄 생성 API 성공 시나리오 테스트")
    void createScheduleTest() throws Exception{
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .memberSid(2L)
                .name("일정1")
                .content("일정1의 내용")
                .scheduleTypeSid(3L)
                .date(LocalDate.of(2022, 10, 1))
                .build();

        // service 객체의 동작 정의
        given(scheduleService.saveSchedule(scheduleRequest))
                .willReturn(ScheduleResponse.builder() //willReturn 의 대상 객체는 @EqualHashCode 가 존재해야 정상 동작 함
                        .sid(1L)
                        .name("일정1")
                        .content("일정1의 내용")
                        .date(LocalDate.of(2022, 10, 1))
                        .member(ScheduleResponse.Member.builder()
                                .sid(2L)
                                .id("djcho@pentasecurity.com")
                                .name("조대준")
                                .role("ADMIN")
                                .workDuty("팀원")
                                .workPosition("과장")
                                .department("아이사인2팀")
                                .build())
                        .createdAt(createdTime)
                        .updatedAt(updatedTime)
                        .build());

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        String content = gson.toJson(scheduleRequest);

        // 실제 controller 호출 및 데이터 검증
        mockMvc.perform(
                        post("/api/v1/schedules")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.sid").exists())
                .andExpect(jsonPath("$.data.name").exists())
                .andExpect(jsonPath("$.data.content").exists())
                .andExpect(jsonPath("$.data.date").exists())
                .andExpect(jsonPath("$.data.member").exists())
                .andExpect(jsonPath("$.data.member.sid").exists())
                .andExpect(jsonPath("$.data.member.id").exists())
                .andExpect(jsonPath("$.data.member.name").exists())
                .andExpect(jsonPath("$.data.member.role").exists())
                .andExpect(jsonPath("$.data.member.workDuty").exists())
                .andExpect(jsonPath("$.data.member.workPosition").exists())
                .andExpect(jsonPath("$.data.member.department").exists())
                .andExpect(jsonPath("$.data.createdAt").exists())
                .andExpect(jsonPath("$.data.updatedAt").exists())
                .andDo(print());

        //지정된 메서드가 실행됐는지 검증하는 역할
        verify(scheduleService).saveSchedule(scheduleRequest);
    }

    @Test
    @DisplayName("스케줄 조회 API 성공 시나리오 테스트")
    void getScheduleTest() throws Exception {
        given(scheduleService.getSchedule(1L))
                .willReturn(ScheduleResponse.builder()
                        .sid(1L)
                        .name("일정1")
                        .content("일정1의 내용")
                        .date(LocalDate.of(2022, 10, 1))
                        .member(ScheduleResponse.Member.builder()
                                .sid(2L)
                                .id("djcho@pentasecurity.com")
                                .name("조대준")
                                .role("ADMIN")
                                .workDuty("팀원")
                                .workPosition("과장")
                                .department("아이사인2팀")
                                .build())
                        .createdAt(createdTime)
                        .updatedAt(updatedTime)
                        .build());

        String scheduleSid = "1";

        // 실제 controller 호출 및 데이터 검증
        mockMvc.perform(
                        get("/api/v1/schedules/" + scheduleSid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.sid").exists())
                .andExpect(jsonPath("$.data.name").exists())
                .andExpect(jsonPath("$.data.content").exists())
                .andExpect(jsonPath("$.data.date").exists())
                .andExpect(jsonPath("$.data.member").exists())
                .andExpect(jsonPath("$.data.member.sid").exists())
                .andExpect(jsonPath("$.data.member.id").exists())
                .andExpect(jsonPath("$.data.member.name").exists())
                .andExpect(jsonPath("$.data.member.role").exists())
                .andExpect(jsonPath("$.data.member.workDuty").exists())
                .andExpect(jsonPath("$.data.member.workPosition").exists())
                .andExpect(jsonPath("$.data.member.department").exists())
                .andExpect(jsonPath("$.data.createdAt").exists())
                .andExpect(jsonPath("$.data.updatedAt").exists())
                .andDo(print());

        //지정된 메서드가 실행됐는지 검증하는 역할
        verify(scheduleService).getSchedule(1L);
    }

    @Test
    @DisplayName("스케줄 갱신 API 성공 시나리오 테스트")
    void updateScheduleTest() throws Exception{
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .memberSid(2L)
                .name("일정1")
                .content("일정1의 내용")
                .scheduleTypeSid(3L)
                .date(LocalDate.of(2022, 10, 1))
                .build();

        // service 객체의 동작 정의
        given(scheduleService.updateSchedule(1L, scheduleRequest))
                .willReturn(ScheduleResponse.builder() //willReturn 의 대상 객체는 @EqualHashCode 가 존재해야 정상 동작 함
                        .sid(1L)
                        .name("일정1")
                        .content("일정1의 내용")
                        .date(LocalDate.of(2022, 10, 1))
                        .member(ScheduleResponse.Member.builder()
                                .sid(2L)
                                .id("djcho@pentasecurity.com")
                                .name("조대준")
                                .role("ADMIN")
                                .workDuty("팀원")
                                .workPosition("과장")
                                .department("아이사인2팀")
                                .build())
                        .createdAt(createdTime)
                        .updatedAt(updatedTime)
                        .build());

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        String content = gson.toJson(scheduleRequest);

        String scheduleSid = "1";
        // 실제 controller 호출 및 데이터 검증
        mockMvc.perform(
                        patch("/api/v1/schedules/" + scheduleSid)
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.sid").exists())
                .andExpect(jsonPath("$.data.name").exists())
                .andExpect(jsonPath("$.data.content").exists())
                .andExpect(jsonPath("$.data.date").exists())
                .andExpect(jsonPath("$.data.member").exists())
                .andExpect(jsonPath("$.data.member.sid").exists())
                .andExpect(jsonPath("$.data.member.id").exists())
                .andExpect(jsonPath("$.data.member.name").exists())
                .andExpect(jsonPath("$.data.member.role").exists())
                .andExpect(jsonPath("$.data.member.workDuty").exists())
                .andExpect(jsonPath("$.data.member.workPosition").exists())
                .andExpect(jsonPath("$.data.member.department").exists())
                .andExpect(jsonPath("$.data.createdAt").exists())
                .andExpect(jsonPath("$.data.updatedAt").exists())
                .andDo(print());

        //지정된 메서드가 실행됐는지 검증하는 역할
        verify(scheduleService).updateSchedule(1L, scheduleRequest);
    }

    @Test
    @DisplayName("스케줄 삭제 API 성공 시나리오 테스트")
    void deleteScheduleTest() throws Exception{
        // service 객체의 동작 정의
        willDoNothing().given(scheduleService).deleteSchedule(1L);

        String scheduleSid = "1";
        // 실제 controller 호출 및 데이터 검증
        mockMvc.perform(
                        delete("/api/v1/schedules/" + scheduleSid))
                .andExpect(status().isOk())
                .andDo(print());

        //지정된 메서드가 실행됐는지 검증하는 역할
        verify(scheduleService).deleteSchedule(1L);
    }
}
