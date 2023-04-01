package com.oasis.service;

import com.oasis.data.dto.request.WorkLogCreateRequest;
import com.oasis.data.dto.request.WorkLogModifyRequest;

public interface WorkLogService {
    void addWorkLog(WorkLogCreateRequest workLogCreateRequest);
    void modifyWorkLog(Long workLogId, WorkLogModifyRequest workLogModifyRequest);
    void removeWorkLog(Long workLogId);
}
