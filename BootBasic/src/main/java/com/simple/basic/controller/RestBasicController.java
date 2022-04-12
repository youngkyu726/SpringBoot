package com.simple.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.basic.command.RestVO;

@RestController //== @Controller + @ResponseBody
public class RestBasicController {

	//produces는 타입을 지정하게 되면, 해당 타입으로 사용자에게 응답하겠다는 뜻.(default = JSON )
	
	@GetMapping(value = "/hello", produces = "text/plain; charset=utf-8")
	public String hello() {
		
		System.out.println("REST API실행됨");
		
		return "안녕하세요";
	}
	
	//produces 생락하면 기본으로 json형식을 가짐
	//jackson-databind라이브러리 필수( 부트에는 기본으로 라이브러리를 가지고 있음 )
	@GetMapping(value = "/getObject", produces = "application/json; charset=utf-8")
	public RestVO getObject() {
		
		return new RestVO(1, "강아지", "puppy");
		
	}
	
	@GetMapping("/getCollection")
	public ArrayList<RestVO> getCollection() {
		
		ArrayList<RestVO> list = new ArrayList<>();
		
		for(int i = 0; i <= 10; i ++) {
			
			RestVO vo = RestVO.builder().num(i)
										.name("강아지" + i)
										.id("Puppy" + i)
										.build();
			list.add(vo);
		}	
		return list;
	}
	
	//map의 형태로 반환
	@GetMapping("/getMap")
	public HashMap<String, Object> getMap() {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("msg", "성공");
		map.put("data", new RestVO(1, "강아지", "Puppy"));
		
		return map;
	}
	
	//----------------------------------------------데이터를 받는 방법-------------------------------------------------------
	
	//http://localhost:8181//getData?key=홍길동&bno=1
	//get방식으로 데이터를 받음
	//리퀘스트파람은 데이터를 필수값으로 넘겨줘야함
	@GetMapping("/getData")
	public RestVO getData(@RequestParam("key") String key,
						  @RequestParam("bno") int bno) {
		
		System.out.println(key);
		System.out.println(bno);
		
		return new RestVO(1, "강아지", "puppy");
	}

	//get 방식으로 데이터를 받음
	//URL주소의 /값/값/값 형태로 받음
	//http://localhost:8181/getPath/asc/desc/1
	@GetMapping("/getPath/{sort}/{rank}/{page}")
	public RestVO getPath(@PathVariable("sort") String sort,
						  @PathVariable("rank") String rank,
						  @PathVariable("page") String page) {
		
		System.out.println(sort);
		System.out.println(rank);
		System.out.println(page);
		
		return new RestVO(1, "강아지", "puppy");
	}
	
	//Post방식 @RequestBody로 JSON을 맵핑
	//데이터를 보낼때(화면에서) 해당형식을 반드시 기술
	@PostMapping("/getJSON")
	public RestVO getJSON(@RequestBody RestVO vo) {
		
		System.out.println(vo.toString());
		
		return new RestVO(1, "강아지", "puppy");
	}
	
	//post방식 - map을 통해 받기
	@PostMapping(value = "/getResult")
	public RestVO getResult(@RequestBody HashMap<String, Object> map) {
		
		System.out.println(map.toString());
		
		return new RestVO(1, "강아지", "puppy");
	}
	
	//Post방식 - consumers를 통한 데이터 형식 제한
	//consumers는 특정타입의 데이터를 받도록 처리하는 옵션(화면에서는 반드시 해당형식으로 데이터를 보내는형식)
	//Content-type : text/plain
	@PostMapping(value = "/getResult2", consumes = "text/plain")
	public RestVO getResult2(@RequestBody String data) {
		
		System.out.println(data);
		
		return new RestVO(1, "강아지", "puppy");
	}
	
	//-----------------------------------응답 문서를 직접 선언----------------------------------
	@PostMapping("/createResponse")
	public ResponseEntity<RestVO> createResponse(@RequestBody HashMap<String, Object> map) {
		
		System.out.println(map.toString());
		
		RestVO vo = new RestVO(1, "강아지", "puppy");//데이터
		HttpHeaders headers = new HttpHeaders();//헤더
		headers.add("Authorization", "JSON WEB TOKEN");
		headers.add("Access-Control-Allow-Origin", "true");
		
		ResponseEntity<RestVO> res = new ResponseEntity<RestVO>(vo, headers, HttpStatus.OK); //(데이터,헤더,상태)
		
		return res;
	}
	//------------------------------------------------------------------------------------------------
	//jqurey 학습후 restAPI확인
	
//	@CrossOrigin("http://127.0.0.1:5502") //post방식은 ip:prot가 동일시 요청을 허용함. 서버가 다르다면 특정서버의 요청을 허용해야 요청처리를 받을수 있음.
//	@PostMapping(value = "/ajaxTest01", produces = "application/json")
//	public ArrayList<RestVO> ajaxTest01(@RequestBody RestVO vo) {
//		System.out.println(vo.toString());
//		
//		ArrayList<RestVO> list = new ArrayList<>();
//		for(int i = 1; i <= 10; i++) {
//			RestVO t = RestVO.builder()
//							  .name("홍길동" + i)
//							  .id("aa123" + i)
//							  .num(i)
//							  .build();
//			list.add(t);
//		}
//		return list;
//	}
	
	@CrossOrigin("http://127.0.0.1:5502") //post방식은 ip:prot가 동일시 요청을 허용함. 서버가 다르다면 특정서버의 요청을 허용해야 요청처리를 받을수 있음.
	@PostMapping(value = "/ajaxTest01", produces = "application/xml")
	public ArrayList<RestVO> ajaxTest01(@RequestBody RestVO vo) {
		System.out.println(vo.toString());
		
		ArrayList<RestVO> list = new ArrayList<>();
		for(int i = 1; i <= 10; i++) {
			RestVO t = RestVO.builder()
							  .name("홍길동" + i)
							  .id("aa123" + i)
							  .num(i)
							  .build();
			list.add(t);
		}
		return list;
	}
}
