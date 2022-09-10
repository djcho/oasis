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

    private final WorkLogService workLogService;

    @GetMapping()
    public void workLog() {

    }
    @PostMapping()
    public void addWorkLog(@RequestBody WorkLogCreateRequest workLogCreateRequest){
        workLogService.addWorkLog(workLogCreateRequest);
    }

    @PatchMapping(value = "{sid}")
    public void modifyWorkLog(@PathVariable Long sid, @RequestBody WorkLogModifyRequest workLogModifyRequest){
        workLogService.modifyWorkLog(sid, workLogModifyRequest);
    }

    @DeleteMapping(value = "{sid}")
    public void removeWorkLog(@PathVariable Long sid){
        workLogService.removeWorkLog(sid);
    }

    
}
