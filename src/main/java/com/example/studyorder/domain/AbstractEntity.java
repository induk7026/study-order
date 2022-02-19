package com.example.studyorder.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;


// MappedSuperclass : 해당 클래스에 Auditing 기능을 포함
// AuditingEntityListener : 해당 클래스에 Auditing 기능을 포함
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

    @CreationTimestamp
    private ZonedDateTime createdAt;
    @UpdateTimestamp
    private ZonedDateTime updatedAt;

}
