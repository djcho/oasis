package com.oasis.data.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Entity 클래스에 상속해 생성/수정일자 자동화 
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseTimeEntity {
    
    @CreatedDate
    @Column(name = "createDttm")
    private LocalDateTime createDttm;
    
    @LastModifiedDate
    @Column(name = "modifyDttm")
    private LocalDateTime modifyDttm;
    
}
