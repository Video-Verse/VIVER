package com.project.viver.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.viver.entity.kopis.Musical;

@Repository
public interface KopisRepository extends JpaRepository<Musical, String>{

}
