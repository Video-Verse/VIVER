package com.project.viver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.viver.entity.Img;

import jakarta.transaction.Transactional;

@Repository
public interface ImgRepository extends JpaRepository<Img, String>, ImgRepositoryCustom {

	@Transactional
	@Query(value = "select nextval('seq_img_temp_nm') seq ", nativeQuery=true)
	Long findBySeq();

	List<Img> findAllByImgIdAndDelYn(String regId, String delYn);
}