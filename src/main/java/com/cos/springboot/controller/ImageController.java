package com.cos.springboot.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//Controller , configuration, Repository

@Controller
public class ImageController {
	
	//yml꺼 가져오기 DI
	@Value("${file.path}")
	private String fileRealPath;
	
	@PutMapping("/image/upload")
	public @ResponseBody String imageUpload(@RequestParam("imgFile") MultipartFile imgFile) {
		//@RequestParam("imgFile") MultipartFile imgFile 변수명 같으니까 리퀘스트파람 생략 가능
		//1. imgFile 출력
		
		//이미지 정보확인
		System.out.println(imgFile.getOriginalFilename());
		System.out.println(imgFile.getContentType());
		System.out.println(imgFile.getSize());
		System.out.println(imgFile.getName());
		try {
			System.out.println(imgFile.getBytes().toString());
			//getBytes가 이미지 실체고 이걸 파일로 변환해서 저장해야한다.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//파일명 충돌방지
		UUID uuid = UUID.randomUUID();
		String uuidFilename = uuid+"_"+imgFile.getOriginalFilename();
		
		
		//이미지 저장 위치: 외부에 잡기/ 나중에 이 경로랑 파일명만 db에 저장하면 된다.
		//nio 로 임포트
		Path filePath = Paths.get(fileRealPath+uuidFilename);
		//하드디스크와 메모리 통신, 통신할댄 무조건 try catch
		//디비에는 uuidFilename 만 넣고 fileRealPath 붙여주기 
		
		try {
			Files.write(filePath, imgFile.getBytes());
			//얘가 스레드 새로 만들어서 만약 10메가 넘어가면 여기서 파일 넣는동안 메인스레드가 ok 뿌려서 엑박뜸-> 
			//사진 용량 제한시키기 아니면 메인스레드 기다리게하기(비동기처리)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ok";
	}

}
