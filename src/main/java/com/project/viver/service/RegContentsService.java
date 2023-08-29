package com.project.viver.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.viver.common.constraint.CommonId;
import com.project.viver.entity.Img;
import com.project.viver.entity.RegContents;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.EntityNotFoundException;
import com.project.viver.repository.RegContentsRepository;
import com.project.viver.service.common.CommonService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RegContentsService {

	private final CommonService commonService;

	private final ImgService imgService;

    private final RegContentsRepository regContentsRepository;

    public Page<RegContents> search(Map<String, String> params, PageRequest pageable) {

        return regContentsRepository.search(params, pageable);
    }

    @Transactional
    public RegContents insert(String entity, List<MultipartFile> files) throws JsonProcessingException {

    	ObjectMapper obj = new ObjectMapper();
		RegContents regContents = obj.convertValue(obj.readValue(entity, Map.class), RegContents.class);

    	regContents.setRegId(commonService.getId(CommonId.REG_CONTENTS.value()));
    	regContents.setCrtDt(LocalDateTime.now());
    	regContents.setMdfnDt(LocalDateTime.now());
    	regContents.setDelYn("N");

    	List<Img> imgs = imgService.upload(files, regContents.getUserId(), regContents.getRegId());
    	if(imgs.size() == 2) {
    		regContents.setFirstImgId(imgs.get(0).getImgId());
    		regContents.setSecondImgId(imgs.get(1).getImgId());
    	} else if(imgs.size() == 1) {
    		regContents.setFirstImgId(imgs.get(0).getImgId());
    	}

        return regContentsRepository.save(regContents);
    }

    @Transactional
    public RegContents update(String entity, List<MultipartFile> files) throws JsonProcessingException {

    	ObjectMapper obj = new ObjectMapper();
		RegContents regContents = obj.convertValue(obj.readValue(entity, Map.class), RegContents.class);

    	regContentsRepository.findByRegIdAndDelYn(regContents.getRegId(), "N")
    						 .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));

    	regContents.setMdfnDt(LocalDateTime.now());

    	if(!files.isEmpty()) {
    		imgService.delete(regContents.getRegId());
    		List<Img> imgs = imgService.upload(files, regContents.getUserId(), regContents.getRegId());
        	if(imgs.size() == 2) {
        		regContents.setFirstImgId(imgs.get(0).getImgId());
        		regContents.setSecondImgId(imgs.get(1).getImgId());
        	} else if(imgs.size() == 1) {
        		regContents.setFirstImgId(imgs.get(0).getImgId());
        	}
    	}

        return regContentsRepository.save(regContents);
    }

    public RegContents delete(String regId) {

    	RegContents regContents = regContentsRepository.findByRegIdAndDelYn(regId, "N")
    												   .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    	regContents.setDelYn("Y");
    	regContents.setMdfnDt(LocalDateTime.now());
    	regContents.setDelDt(LocalDateTime.now());

    	imgService.delete(regId);

    	return regContentsRepository.save(regContents);
    }
}