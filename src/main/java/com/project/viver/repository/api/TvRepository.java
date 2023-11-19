package com.project.viver.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.viver.entity.tmdb.Tv;


@Repository
public interface TvRepository extends JpaRepository<Tv, String>, TvRepositoryCustom{

}
