package com.oasis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oasis.common.constant.MemberRole;
import com.oasis.controller.MemberController;
import com.oasis.data.dto.request.MemberCreateRequestDto;
import com.oasis.service.DepartmentService;
import com.oasis.service.MemberService;
import com.oasis.service.WorkDutyService;
import com.oasis.service.WorkPositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("devsora")
public class MemberServiceTests {
    
    @Autowired
    WebApplicationContext ctx;
    
    @Autowired
    ObjectMapper objectMapper;
    
    MockMvc mockMvc;
    
    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
    
    @Test
    public void getUserApiTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/users");
        ResultActions actions = mockMvc.perform(request);
        actions.andExpect(status().isOk()).andDo(print());
    }
    
    @Test
    public void createUserApiTest() throws Exception {
        
//        MemberCreateRequestDto dto = new MemberCreateRequestDto();
//        dto.setId("sora@pentasecurity.com");
//        dto.setName("sora");
//        dto.setPassword("sora1234");
//        dto.setMemberRole(MemberRole.NORMAL);
//        dto.setDepartmentSid("6");
//        dto.setWorkPositionSid("2");
//        dto.setWorkDutySid("2");
//
//        String content = objectMapper.writeValueAsString(dto);
//        RequestBuilder request = MockMvcRequestBuilders.post("/users")
//                .content(content).contentType(MediaType.APPLICATION_JSON);
//        ResultActions actions = mockMvc.perform(request);
//        actions.andExpect(status().isOk());
        
    }
    
    
    @Test
    public void createUserTest() {
        /*
        // start of creating temporary Department 
        Department level0 = Department.builder().level(0).parentSid(0L).name("/").build();
        Department level1 = Department.builder().level(1).parentSid(0L).name("보안기술연구소").build();
        Department level2 = Department.builder().level(2).parentSid(1L).name("아이사인개발부").build();
        Department level3_1 = Department.builder().level(3).parentSid(2L).name("1팀").build();
        Department level3_2 = Department.builder().level(3).parentSid(2L).name("2팀").build();
        Department level3_3 = Department.builder().level(3).parentSid(2L).name("3팀").build();

        List<Department> departments = new ArrayList<>();
        departments.add(level0);
        departments.add(level1);
        departments.add(level2);
        departments.add(level3_1);
        departments.add(level3_2);
        departments.add(level3_3);

        departmentService.saveAll(departments);
        // end of creating temporary Department 

        // start of creating temporary WorkPosition
        WorkPosition position1 = WorkPosition.builder().level(1L).name("부장").build();
        WorkPosition position2 = WorkPosition.builder().level(2L).name("차장").build();
        WorkPosition position3 = WorkPosition.builder().level(3L).name("과장").build();
        WorkPosition position4 = WorkPosition.builder().level(4L).name("대리").build();
        WorkPosition position5 = WorkPosition.builder().level(5L).name("사원").build();

        workPositionService.createWorkPosition(position1);
        workPositionService.createWorkPosition(position2);
        workPositionService.createWorkPosition(position3);
        workPositionService.createWorkPosition(position4);
        workPositionService.createWorkPosition(position5);
        // end of creating temporary WorkPosition

        // start of creating temporary WorkDuty
        WorkDuty workDuty1 = WorkDuty.builder().name("팀장").build();
        WorkDuty workDuty2 = WorkDuty.builder().name("팀원").build();

        workDutyService.createWorkDuty(workDuty1);
        workDutyService.createWorkDuty(workDuty2);
        // end of creating temporary WorkDuty 
        
        
        IntStream.range(1,31).forEach(i -> {
            
            Department userDepartment = i/10 + 1 == 1 ? level3_1 : i/10 + 1 == 2 ? level3_2 : level3_3;
            WorkDuty userWorkDuty = i%10 == 1 ? workDuty1 : workDuty2;
            WorkPosition userWorkPosition = i%10 < 5 ? position2 : position5;
            
            memberService.createMember(
                    Member.builder()
                            .id("memberid"+i+"@pentasecurity.com")
                            .name("memberName"+i)
                            .password("1234")
                            .department(userDepartment)
                            .memberRole(MemberRole.NORMAL)
                            .workDuty(userWorkDuty)
                            .workPosition(userWorkPosition)
                            .build()
            );
            
            memberService.getAllMembers().stream().forEach(System.out::println);
            
        });
         */
        

    }
    
    
    
    public void deleteAllData() {
        // TODO :: 
    }
    

    
    
}
