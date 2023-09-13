package com.project.viver.entity.common;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

	private String delYn;
    private LocalDateTime crtDt;
    private LocalDateTime mdfnDt;
    private LocalDateTime delDt;

}
