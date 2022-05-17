package com.wdkim.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wdkim.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Controller
@Log4j
public class UploadController {
	private static final String UPLOAD_FOLDER = "c:/upload";
	
	@GetMapping("uploadForm")
	public void uploadForm() {
		log.info("uploadForm get");
	}
	
	@PostMapping("uploadForm")
	public void uploadForm(@ModelAttribute MultipartFile[] uploadFile, Model model) {
		log.info("uploadForm post");
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("------------------------------------------");
			log.info("file name :: " + multipartFile.getOriginalFilename());
			log.info("file size :: " + multipartFile.getSize());
			try {
				multipartFile.transferTo(new File("c:/upload", multipartFile.getOriginalFilename()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@GetMapping("uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax get");
	}
	
	@PostMapping("upload") @ResponseBody
	public List<AttachFileDTO> upload(MultipartFile[] files, Model model) throws IllegalStateException, IOException {
		log.info("upload post");
		List<AttachFileDTO> list = new ArrayList<>();
		File uploadPath = new File(UPLOAD_FOLDER, getFolder());
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : files) {
			log.info("------------------------------------------");
			String origin = multipartFile.getOriginalFilename();
			
			String ext = "";
			if(origin.lastIndexOf(".") != -1) { //확장자가 없는 경우
				ext = origin.substring(origin.lastIndexOf("."));
			}
			String uuid = UUID.randomUUID().toString() + ext;
			
			File file = new File(uploadPath, uuid);
			multipartFile.transferTo(file);
			
			AttachFileDTO dto = new AttachFileDTO(origin, uuid, getFolder(), isImage(file));
			
			if(isImage(file)) {
				Thumbnails
				.of(file)
				.sourceRegion(Positions.CENTER, 200, 200)
				.size(200, 200)
				.toFile(new File(uploadPath, "s_" + uuid));
			}
			list.add(dto);
		}
		return list;
	}
	
	@GetMapping("display") @ResponseBody
	public ResponseEntity<byte[]> getFile(AttachFileDTO dto) {
		log.info(dto);
		File file = new File(UPLOAD_FOLDER, dto.getPath() + "/" + dto.getUuid());
		
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders headers = new HttpHeaders(); // 응답시에 컨텐트타입을 정해주기위함.
		try {
			headers.add("Content-Type", Files.probeContentType(file.toPath())); // 해당파일의 마임타입으로 헤더 지정
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			result = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR); // 예외발생시 500의 응답을 주겠다.
			// 노서치 익셉션일 경우를 따로 나눠서 404줘도된다
		}
		return result;
	}
	@GetMapping("download") @ResponseBody
	public ResponseEntity<byte[]> download(AttachFileDTO dto) {
		log.info(dto);
		File file = new File(UPLOAD_FOLDER, dto.getPath() + "/" + dto.getUuid());
		
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
			// url인코더 쓰면 " "이 "+"이 되는걸 방지
			headers.add("Content-Disposition", "attachment; filename=" + new String(dto.getOrigin().getBytes("utf-8"), "iso-8859-1"));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			result = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@PostMapping("deleteFile") @ResponseBody
	public String deleteFile(AttachFileDTO dto) {
		log.info(dto);
		
		File file = new File(UPLOAD_FOLDER, dto.getPath() + "/" + dto.getUuid());
		file.delete();
		if(dto.isImage()) {
			file = new File(UPLOAD_FOLDER, dto.getPath() + "/s_" + dto.getUuid());
			file.delete();
		}
		
		return "success";
	}
	
	private String getFolder() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}
	
	private boolean isImage(File file) throws IOException {
		String mime = Files.probeContentType(file.toPath());
		
		if(mime == null || mime.equals("image/x-icon")) return false;
		return mime.startsWith("image");
	}
	
}
