package com.project.viver.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.common.response.model.SingleResult;
import com.project.viver.common.response.service.ResponseService;
import com.project.viver.service.api.MovieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tmdb")
@RequiredArgsConstructor
public class TmdbController {

	@Autowired
	private MovieService tmdbService;

	@Autowired
	private ResponseService responseService;

    @GetMapping(value = "/discover")
    public SingleResult<Map<String, Object>> dashboard() {
        return responseService.getSingleResult(tmdbService.getDiscover());
    }

}
