package com.oasis.service;

import com.oasis.data.dto.request.WorklogCreateRequest;
import com.oasis.data.dto.request.WorklogModifyRequest;
import org.springframework.stereotype.Service;

@Service
public interface WorklogService {
    public void addWorklog(WorklogCreateRequest worklogCreateRequest);
    public void modifyWorklog(Long sid, WorklogModifyRequest worklogModifyRequest);
    public void removeWorklog(Long sid);
}
