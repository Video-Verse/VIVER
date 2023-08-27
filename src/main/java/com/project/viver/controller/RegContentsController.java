package com.project.viver.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.common.response.model.SingleResult;
import com.project.viver.common.response.service.ResponseService;
import com.project.viver.entity.reg.RegContents;
import com.project.viver.service.reg.RegContentsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/regContents")
public class RegContentsController {
	
	private final RegContentsService regContentsService;
	
	private final ResponseService responseService;
	
	@ResponseBody
	@PostMapping("/insert")
	public SingleResult<RegContents> insert(@RequestBody RegContents regContents) {
		return responseService.getSingleResult(regContentsService.insert(regContents));
	}
	
	@ResponseBody
	@PostMapping("/update")
	public SingleResult<RegContents> update(@RequestBody RegContents regContents) {
		return responseService.getSingleResult(regContentsService.update(regContents));
	}
	
	@ResponseBody
	@PostMapping("/delete")
	public SingleResult<RegContents> delete(@RequestBody Map<String, Object> params) {
		return responseService.getSingleResult(regContentsService.delete(params.get("regId").toString()));
	}

}
