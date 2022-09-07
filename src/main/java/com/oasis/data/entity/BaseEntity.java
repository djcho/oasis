package com.oasis.data.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity 클래스에 상속해 sid, 생성/수정일자 자동화 
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;
    
    @CreatedDate
    @Column(name = "createDttm")
    private LocalDateTime createDttm;
    
    @LastModifiedDate
    @Column(name = "modifyDttm")
    private LocalDateTime modifyDttm;
    
}
