package com.project.viver.common.response.model;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResult<T> extends CommonResult {
    private Page<T> pagingList;
}