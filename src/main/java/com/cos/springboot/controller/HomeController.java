package com.cos.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.springboot.dto.RequestPutDto;
import com.cos.springboot.dto.RequestUserDto;
import com.cos.springboot.dto.ResponseData;

@Controller
@RequestMapping("/home") //진입로 지정: 모든 애들 주소 앞에 /home 붙여준거
public class HomeController {

	//localhost:8080/
	//localhost:8080  이거 둘다 맵핑해줄려면 {"","/"} 이렇게 / 하나면 하려면 그냥"/"중괄호없이
	@GetMapping({"","/"})
	public String home() {
		
		return "home";
	}
	
	@GetMapping("/hello")
	public String hello_get() {
		//DB select 로직 - 모델에 담기
		return "hello";
	} //get은 하이퍼링크 쓸 필요 없이 하이퍼링크로 해서 데이터받으면 됨
	
	@PostMapping("/hello")
	public @ResponseBody ResponseData hello_post(@RequestBody RequestUserDto requestUserDto) {
		//ajax 로 할거라서 페이지 리턴안하니까 뷰리졸버 관여안하게 리스폰스바디하기
		// 문자 하나를 리턴하더라도 String 으로 리턴하지 말고 오브젝트로 리턴하기 (그래야 잭슨이 제이슨으로 리턴해준다)
		//DB insert 로직
		System.out.println(requestUserDto.getId());
		return new ResponseData(200, "ok");
	} //form태그, 제이쿼리
	
	@DeleteMapping("/hello")
	public @ResponseBody ResponseData hello_delete(@RequestBody RequestUserDto requestUserDto) {
		//DB delete 로직
		System.out.println(requestUserDto.getId());
		return new ResponseData(200, "ok");
	}
	
	@PutMapping("/hello")
	public @ResponseBody ResponseData hello_put(@RequestBody RequestPutDto requestPutDto) {
		//DB delete 로직
		System.out.println(requestPutDto.getId());
		System.out.println(requestPutDto.getPhone());
		return new ResponseData(200, "ok");
	}
}
