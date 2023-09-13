package com.project.viver.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.viver.entity.tmdb.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, String>, MovieRepositoryCustom{

}
