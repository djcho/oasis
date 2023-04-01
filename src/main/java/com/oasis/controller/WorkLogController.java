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

    @PatchMapping(value = "{id}")
    public void modifyWorkLog(@PathVariable Long id, @RequestBody WorkLogModifyRequest workLogModifyRequest){
        workLogService.modifyWorkLog(id, workLogModifyRequest);
    }

    @DeleteMapping(value = "{id}")
    public void removeWorkLog(@PathVariable Long id){
        workLogService.removeWorkLog(id);
    }

    
}
