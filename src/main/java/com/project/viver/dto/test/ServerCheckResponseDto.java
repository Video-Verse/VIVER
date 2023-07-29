package com.project.viver.dto.test;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServerCheckResponseDto {

    private String health;
    private List<String> activeProfiles;

}

