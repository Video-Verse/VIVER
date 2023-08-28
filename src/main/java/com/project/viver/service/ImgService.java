package com.project.viver.service;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.viver.common.constraint.CommonId;
import com.project.viver.entity.Img;
import com.project.viver.repository.ImgRepository;
import com.project.viver.service.common.CommonService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ImgService {

	private final CommonService commonService;

	private final ImgRepository imgRepository;


    public List<Img> upload(List<MultipartFile> files, String userId, String regId) {
    	List<Img> results = new ArrayList<>();

    	String imgLoc = "viverImg/" + userId + "/" + regId + "/" + LocalDate.now().toString();

    	for(int i=0; i<files.size(); i++) {
    		MultipartFile f = files.get(i);

    		String imgNm = f.getOriginalFilename();
    		String imgExt = imgNm.substring(imgNm.lastIndexOf("."));
    		String tempNm = getFileNameSeq();

    		try {
    			File dir = new File(imgLoc);
				FileUtils.forceMkdir(dir);

				File file = new File(imgLoc + "/" + tempNm + imgExt);
				f.transferTo(file);

				Img imgVO = Img.builder()
						  .imgId(commonService.getId(CommonId.IMG.value()))
						  .imgLoc(imgLoc)
						  .imgNm(imgNm)
						  .imgExt(imgExt)
						  .imgSize(file.length())
						  .tempLoc(tempNm)
						  .tempNm(tempNm)
						  .orderNo(Integer.toUnsignedLong(i)).build();

				imgVO.setDelYn("N");
				imgVO.setCrtDt(LocalDateTime.now());
				imgVO.setMdfnDt(LocalDateTime.now());

				results.add(imgRepository.save(imgVO));

			} catch (IOException e) {
				e.printStackTrace();
			}
    	}

    	return results;
    }

    private String getFileNameSeq() {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.KOREA);
		return dataFormat.format(new Date()) + String.valueOf(imgRepository.findBySeq());
	}
}