package com.wdkim.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wdkim.domain.BoardAttachVO;
import com.wdkim.mapper.BoardAttachMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
@AllArgsConstructor
public class FileCheckTask {
	
	private BoardAttachMapper mapper;
	
	
	@Scheduled(cron="0 0 2 * * *") // 매일 새벽 두시마다 적용
	public void checkFiles() {
		log.warn("File Check Task");
		log.warn("======================================");
		log.warn(getFolderYesterday());
		
		File file = new File("c:/upload", getFolderYesterday());
		if(!file.exists()) {
			return;
		}
		
		
		log.warn("======================================");
		List<BoardAttachVO> dbFiles = mapper.getOldFiles(); // 디비에 저장된 파일들
		//디비내의 첨부파일을 파일타입 리스트로 변환
		List<File> dbFiles2 = dbFiles.stream().map(attach -> new File(file, attach.getUuid()))
		.collect(Collectors.toList());
		
		//predicate은 입력값 하나에 반환값 불린이다
		//썸네일추가
		dbFiles.stream()
		.filter(BoardAttachVO::isImage)
		.map(attach -> new File(file, "s_" + attach.getUuid()))
		.forEach(dbFiles2::add);
			
		// 디비에 존재하지 않는 파일 삭제
		Arrays.asList(file.listFiles(f->!dbFiles2.contains(f))).forEach(File::delete);;
		
	}
	
	
	private String getFolderYesterday() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime() - 1000 * 60 * 60 * 24 * 1);
	}
	
}
