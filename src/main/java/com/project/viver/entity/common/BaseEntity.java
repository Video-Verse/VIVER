package com.project.viver.entity.common;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
public abstract class BaseEntity {
	
	@LastModifiedBy
	private String delYn;

    @CreatedBy
    @Column(updatable = false)
    private String crtDt;

    @LastModifiedBy
    private String mdfnDt;
    
    @LastModifiedBy
    private String delDt;

}
