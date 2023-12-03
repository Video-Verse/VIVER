package com.project.viver.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.common.response.model.ListResult;
import com.project.viver.common.response.model.SingleResult;
import com.project.viver.common.response.service.ResponseService;
import com.project.viver.service.api.MovieService;
import com.project.viver.service.api.TvService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tmdb")
@RequiredArgsConstructor
public class TmdbController {

	@Autowired
	private MovieService tmdbService;

	@Autowired
	private TvService dramaService;

	@Autowired
	private ResponseService responseService;

    @GetMapping(value = "/movie")
    public SingleResult<Map<String, Object>> getMovieDiscover() {
        return responseService.getSingleResult(tmdbService.getMovieDiscover());
    }

    @GetMapping(value = "/drama")
    public SingleResult<Map<String, Object>> getDramaDiscover() {
        return responseService.getSingleResult(dramaService.getDramaDiscover());
    }

}
