package com.project.viver.repository.reg;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.viver.entity.reg.RegContents;

@Repository
public interface RegContentsRepository extends JpaRepository<RegContents, String>, RegContentsRepositoryCustom {
	
	Optional<RegContents> findByRegIdAndDelYn(String regId, String DelYn);
	
}
