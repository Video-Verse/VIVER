package com.project.viver.service.reg;


import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.viver.common.constraint.CommonId;
import com.project.viver.entity.reg.RegContents;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.EntityNotFoundException;
import com.project.viver.repository.reg.RegContentsRepository;
import com.project.viver.service.common.CommonService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RegContentsService {
	
	private final CommonService commonService;

    private final RegContentsRepository regContentsRepository;
    
    public Page<RegContents> search(Map<String, String> params, PageRequest pageable) {
    	
        return regContentsRepository.search(params, pageable);
    }

    public RegContents insert(RegContents regContents) {
    	
    	regContents.setRegId(commonService.getId(CommonId.REG_CONTENTS.value()));
    	regContents.setCrtDt(LocalDateTime.now());
    	regContents.setMdfnDt(LocalDateTime.now());
    	regContents.setDelYn("N");
    	
        return regContentsRepository.save(regContents);
    }
    
    public RegContents update(RegContents regContents) {
    	
    	regContentsRepository.findByRegIdAndDelYn(regContents.getRegId(), "N")
    						 .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    	
    	regContents.setMdfnDt(LocalDateTime.now());
    	
        return regContentsRepository.save(regContents);
    }
    
    public RegContents delete(String regId) {
    	
    	RegContents regContents = regContentsRepository.findByRegIdAndDelYn(regId, "N")
    												   .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    	regContents.setDelYn("Y");
    	regContents.setMdfnDt(LocalDateTime.now());
    	regContents.setDelDt(LocalDateTime.now());
    	
    	return regContentsRepository.save(regContents);
    }
}