package com.oasis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oasis.common.constant.MemberRole;
import com.oasis.data.dto.request.MemberCreationRequest;
import com.oasis.data.entity.Department;
import com.oasis.data.entity.Member;
import com.oasis.data.entity.WorkDuty;
import com.oasis.data.entity.WorkPosition;
import com.oasis.service.DepartmentService;
import com.oasis.service.MemberService;
import com.oasis.service.WorkDutyService;
import com.oasis.service.WorkPositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@ActiveProfiles("devsora")
public class MemberServiceTests {
    
    @Autowired
    WebApplicationContext ctx;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    WorkPositionService workPositionService;
    
    @Autowired
    WorkDutyService workDutyService;
    
    @Autowired
    DepartmentService departmentService;
    
    @Autowired
    MemberService memberService;
    
    MockMvc mockMvc;
    
    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
    
    @Test
    public void getUserApiTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/members");
        ResultActions actions = mockMvc.perform(request);
        actions.andExpect(status().isOk()).andDo(print());
    }
    
    @Test
    public void createUserApiTest() throws Exception {
        MemberCreationRequest dto = new MemberCreationRequest();
        dto.setId("sora@pentasecurity.com");
        dto.setName("sora");
        dto.setPassword("sora1234");
        dto.setMemberRole(MemberRole.NORMAL);
        dto.setDepartmentSid(6L);
        dto.setWorkPositionSid(2L);
        dto.setWorkDutySid(2L);
        
        String content = objectMapper.writeValueAsString(dto);
        RequestBuilder request = MockMvcRequestBuilders.post("/users")
                .content(content).contentType(MediaType.APPLICATION_JSON);
        ResultActions actions = mockMvc.perform(request);
        actions.andExpect(status().isOk());
    }
    
    
    @Test
    public void createDataForTest() {
        
        // start of creating temporary Department 
        Department level0 = Department.builder().level(0).parentSid(0L).name("/").build();
        Department level1 = Department.builder().level(1).parentSid(0L).name("?????????????????????").build();
        Department level2 = Department.builder().level(2).parentSid(1L).name("?????????????????????").build();
        Department level3_1 = Department.builder().level(3).parentSid(2L).name("1???").build();
        Department level3_2 = Department.builder().level(3).parentSid(2L).name("2???").build();
        Department level3_3 = Department.builder().level(3).parentSid(2L).name("3???").build();

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
        WorkPosition position1 = WorkPosition.builder().level(1L).name("??????").build();
        WorkPosition position2 = WorkPosition.builder().level(2L).name("??????").build();
        WorkPosition position3 = WorkPosition.builder().level(3L).name("??????").build();
        WorkPosition position4 = WorkPosition.builder().level(4L).name("??????").build();
        WorkPosition position5 = WorkPosition.builder().level(5L).name("??????").build();

        workPositionService.createWorkPosition(position1);
        workPositionService.createWorkPosition(position2);
        workPositionService.createWorkPosition(position3);
        workPositionService.createWorkPosition(position4);
        workPositionService.createWorkPosition(position5);
        // end of creating temporary WorkPosition

        // start of creating temporary WorkDuty
        WorkDuty workDuty1 = WorkDuty.builder().name("??????").build();
        WorkDuty workDuty2 = WorkDuty.builder().name("??????").build();

        workDutyService.createWorkDuty(workDuty1);
        workDutyService.createWorkDuty(workDuty2);
        // end of creating temporary WorkDuty 
        
        /*
        
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
