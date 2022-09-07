package com.oasis.controller;

import com.oasis.data.dto.request.WorklogCreateRequest;
import com.oasis.data.dto.request.WorklogModifyRequest;
import com.oasis.service.WorklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/worklogs")
@RequiredArgsConstructor
public class WorklogController {

    private final WorklogService worklogService;

    @GetMapping()
    public void worklog() {

    }
    @PostMapping()
    public void addWorklog(@RequestBody WorklogCreateRequest worklogCreateRequest){
        worklogService.addWorklog(worklogCreateRequest);
    }

    @PatchMapping(value = "{sid}")
    public void modifyWorklog(@PathVariable Long sid, @RequestBody WorklogModifyRequest worklogModifyRequest){
        worklogService.modifyWorklog(sid, worklogModifyRequest);
    }

    @DeleteMapping(value = "{sid}")
    public void removeWoklog(@PathVariable Long sid){
        worklogService.removeWorklog(sid);
    }

    
}
