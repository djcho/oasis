package com.oasis.controller;

import com.oasis.data.dto.request.WorkLogCreateRequest;
import com.oasis.data.dto.request.WorkLogModifyRequest;
import com.oasis.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/worklogs")
@RequiredArgsConstructor
public class WorkLogController {

    private final WorkLogService worklogService;

    @GetMapping()
    public void workLog() {

    }
    @PostMapping()
    public void addWorkLog(@RequestBody WorkLogCreateRequest worklogCreateRequest){
        worklogService.addWorkLog(worklogCreateRequest);
    }

    @PatchMapping(value = "{sid}")
    public void modifyWorkLog(@PathVariable Long sid, @RequestBody WorkLogModifyRequest worklogModifyRequest){
        worklogService.modifyWorkLog(sid, worklogModifyRequest);
    }

    @DeleteMapping(value = "{sid}")
    public void removeWoklog(@PathVariable Long sid){
        worklogService.removeWorkLog(sid);
    }

    
}
