package com.project.viver.dto.test;

import org.hibernate.validator.constraints.NotBlank;

import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("deprecation")
@Getter @Setter
public class BindExceptionTestDto {

    @NotBlank(message = "해당 값은 필수 입력값입니다.")
    private String value1;

    @Max(value = 10, message = "최대 입력값은 10입니다.")
    private Integer value2;

}
