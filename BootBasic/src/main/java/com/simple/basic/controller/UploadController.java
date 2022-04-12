package com.simple.basic.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileupload")
public class UploadController {
	
	//application.properties파일의 해당 변수명을 얻어옵니다
	@Value("${project.upload.path}")
	private String uploadPath;
	
	//업로드 화면처리
	@GetMapping("/upload")
	public String upload() {
		
		return "fileupload/upload";
	}
	
	//폴더생성함수
	public String makeFolder() {
		
		//날짜별로 폴더생성
		DateTimeFormatter datatime = DateTimeFormatter.ofPattern("yyMMdd");
		
		String date = LocalDateTime.now().format(datatime);
		
		File file = new File(uploadPath + "\\" + date ); //java.io (업로드 경로 \\ 폴더명)
		if(file.exists() == false ) { //폴더가 존재하면 true, 존재하지않으면 false
			file.mkdir(); //폴더가 생성
		}
		
		return date;
	}
	
	//단일 업로드
	@PostMapping("/upload_ok")
	public String upload_ok(@RequestParam("file") MultipartFile file) {
		
//		System.out.println(file.toString());
		String originname = file.getOriginalFilename();
		//브라우저별로 파일에 전체경로가 입력되는 경우가 발생
		String filename = originname.substring(originname.lastIndexOf("\\") +1); // \\기준으로 파일명만 추출
		//파일크기
		Long size = file.getSize();
		//날짜별로 폴더생성
		String filepath = makeFolder(); //년월일 형식의 폴더명
		//랜덤값을 생성(파일명 중복처리)
		String uuids = UUID.randomUUID().toString(); //16진수 랜덤값
		//최종 업로드 경로
		String savename = uploadPath + "\\" + filepath + "\\" + uuids + "_" + filename;
		
		try {
			//파일 업로드 작업을 한번에 처리
			File f = new File(savename);
			file.transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일처리중 에러");
		}
		
		//데이터베이스 저장될 값
		System.out.println(originname);
		System.out.println(filename);
		System.out.println(size);
		System.out.println(filepath);
		System.out.println(savename);
		
		return "fileupload/upload_ok";
	}
	
	//복수파일 업로드
	@PostMapping("/upload_ok2")
	public String upload_ok2(@RequestParam("file") List<MultipartFile> list) {
		
		//빈값은 filter기능을 통해 제거
		list = list.stream().filter((f) -> f.isEmpty() == false).collect( Collectors.toList());
		
		for(MultipartFile file : list) {
			
			String originname = file.getOriginalFilename();
			//브라우저별로 파일에 전체경로가 입력되는 경우가 발생
			String filename = originname.substring(originname.lastIndexOf("\\") +1); // \\기준으로 파일명만 추출
			//파일크기
			Long size = file.getSize();
			//날짜별로 폴더생성
			String filepath = makeFolder(); //년월일 형식의 폴더명
			//랜덤값을 생성(파일명 중복처리)
			String uuids = UUID.randomUUID().toString(); //16진수 랜덤값
			//최종 업로드 경로
			String savename = uploadPath + "\\" + filepath + "\\" + uuids + "_" + filename;
			
			try {
				//파일 업로드 작업을 한번에 처리
				File f = new File(savename);
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("파일처리중 에러");
			}
			
			//데이터베이스 저장될 값
			System.out.println(originname);
			System.out.println(filename);
			System.out.println(size);
			System.out.println(filepath);
			System.out.println(savename);
			
		}
		
		return "fileupload/upload_ok";
	}
	//비동기업로드
	//화면에서 json형식으로 넘어오지 않기 때문에 @RequestBody가 필요없음
	@ResponseBody
	@PostMapping("/upload_ok3")
	public String upload_ok3(@RequestParam("file") MultipartFile file,
							 @RequestParam("writer") String writer) {
		
		String originname = file.getOriginalFilename();
		//브라우저별로 파일에 전체경로가 입력되는 경우가 발생
		String filename = originname.substring(originname.lastIndexOf("\\") +1); // \\기준으로 파일명만 추출
		//파일크기
		Long size = file.getSize();
		//날짜별로 폴더생성
		String filepath = makeFolder(); //년월일 형식의 폴더명
		//랜덤값을 생성(파일명 중복처리)
		String uuids = UUID.randomUUID().toString(); //16진수 랜덤값
		//최종 업로드 경로
		String savename = uploadPath + "\\" + filepath + "\\" + uuids + "_" + filename;
		
		try {
			//파일 업로드 작업을 한번에 처리
			File f = new File(savename);
			file.transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일처리중 에러");
		}
		
		//데이터베이스 저장될 값
		System.out.println(originname);
		System.out.println(filename);
		System.out.println(size);
		System.out.println(filepath);
		System.out.println(savename);
		
		System.out.println(file);
		System.out.println(writer);
		
		return "success";
	}
}
