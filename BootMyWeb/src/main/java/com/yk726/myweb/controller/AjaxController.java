package com.yk726.myweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yk726.myweb.command.CategoryVO;
import com.yk726.myweb.product.ProductService;

@RestController
public class AjaxController {

	
	//프로덕트 서비스 영역으로 연결
	@Autowired
	ProductService productService;
	
	//업로드경로
	@Value("${project.upload.path}")
	private String uploadpath;
	
	@GetMapping("/getCategory")
	public ArrayList<CategoryVO> getCategory() {
		
		ArrayList<CategoryVO> list = productService.getCategory();
		//화면에 필요한 형시긍로 변경해서 List<map>반환해도 됨.
		
		return list;
	}
	
	//두, 세번째 카테고리
	   @GetMapping("/getCategoryChild/{group_id}/{category_lv}/{category_detail_lv}")
	   public ArrayList<CategoryVO> getCategoryChild(@PathVariable("group_id") String group_id,
	                                      @PathVariable("category_lv") int category_lv,
	                                      @PathVariable("category_detail_lv") int category_detail_lv) {
		   
		   //마이바티스를 전달하기 위해 vo에 저장
		   CategoryVO vo = CategoryVO.builder()
				   					 .group_id(group_id)
				   					 .category_lv(category_lv)
				   					 .category_detail_lv(category_detail_lv)
				   					 .build();
		   //서비스영역호출(조회)
		   ArrayList<CategoryVO> list = productService.getCategoryChild(vo);
		   
	      return list;
	   }
	   
	   //이미지 데이터 맵핑
	   @GetMapping("/display")
	   public byte[] display(@RequestParam("filename") String filename,
			   				 @RequestParam("uuid") String uuid,
			   				 @RequestParam("filepath") String filepath) {
//		   System.out.println(filepath + "\\" + uuid + "_" + filename);
		   
		   File file = new File(uploadpath + "\\" + filepath + "\\" + uuid + "_" + filename);

		   byte[] result = null;
		   try {
			   //경로의 파일을 읽어서 바이트 배열형으로 파일정보를 반환
			   result = FileCopyUtils.copyToByteArray(file);
		
		   } catch (IOException e) {
			e.printStackTrace();
		   }
		   
		   
		   return result;
	   }
}
