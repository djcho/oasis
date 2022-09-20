package com.oasis.service;

import com.oasis.data.dto.request.WorkLogCreateRequest;
import com.oasis.data.dto.request.WorkLogModifyRequest;
import org.springframework.stereotype.Service;

public interface WorkLogService {
    void addWorkLog(WorkLogCreateRequest workLogCreateRequest);
    void modifyWorkLog(Long sid, WorkLogModifyRequest workLogModifyRequest);
    void removeWorkLog(Long sid);
}
