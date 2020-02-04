package com.cos.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.springboot.dto.RequestMemDeleteDto;
import com.cos.springboot.dto.RequestMemJoinDto;
import com.cos.springboot.dto.RequestMemUpdateDto;
import com.cos.springboot.dto.ResponseDto;
import com.cos.springboot.model.Mem;
import com.cos.springboot.repository.MemRepository;

@Controller
public class MemController {
	
	@Autowired
	private MemRepository memRepository;
	
	//username, password, email, 
	@PostMapping("/mem/api/join")
	//dml 은 페이지 리턴 해줄 필요 없다. 응답만 해주면된다. get은 페이지리턴 해야함.
	public @ResponseBody ResponseEntity<?> memApiJoin(@Valid @RequestBody RequestMemJoinDto requestMemJoinDto, BindingResult bindResult) {
		//Mem 으로 받으면 안된다. mem 은 db용 클래스. dto 따로 만들어주기
		
		if(bindResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error:bindResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
		}
		int result = memRepository.save(requestMemJoinDto);
		
		if(result==1) {
			return new ResponseEntity<ResponseDto>(new ResponseDto(200,"ok"),HttpStatus.CREATED);
//			//httpstatus.ok 는 200
//			//post가 아닌 put, delete 는 ok. 
//			//db에 새로운 행이 생기는건 201 created 로 해주는게 좋다.
		}else {
			return new ResponseEntity<ResponseDto>(new ResponseDto(200,"fail"),HttpStatus.BAD_REQUEST);
//			// 다양한 에러 상황 생각해서 넣어주기
//			//1. unique인데 중복된 값을 넣었을 때 2.notnull인데 데이터 한값 안넣었을때 
//			//세세하게 짜야한다. 
//			//지금은 배우는 단계니까 안들어갔다는 메세지만 띄워주기= bad_request
		}

	}
	
	@GetMapping("/mem")
	public String mems(Model model) {
		List<Mem> mems = memRepository.findAll();
		
		model.addAttribute("mems",mems);
		return "mem/list";
	}
	
	@GetMapping("/mem/{id}") //패스밸류
	public String update(@PathVariable int id, Model model) {
		Mem mem = memRepository.findById(id);
		
		model.addAttribute("mem",mem);
		return "mem/update";
	}
	
	@PutMapping("/mem/api/update") 
	//회원수정성공 메세지가 아니라 그 데이터를 보고싶은 경우 제네릭을 ? 로 하고 dto 객체 타입으로 넣어주기
	public @ResponseBody ResponseEntity<?> updateProc(@RequestBody RequestMemUpdateDto requestMemUpdateDto) {
		
		int result = memRepository.update(requestMemUpdateDto);
		if(result==1) {
		return new ResponseEntity<String>("ok", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	
	}
	
	@DeleteMapping("/mem/api/delete") 
	//회원수정성공 메세지가 아니라 그 데이터를 보고싶은 경우 제네릭을 ? 로 하고 dto 객체 타입으로 넣어주기
	public @ResponseBody ResponseEntity<?> delete(@RequestBody RequestMemDeleteDto requestMemDeleteDto) {
		
		int result = memRepository.delete(requestMemDeleteDto);
		if(result==1) {
		return new ResponseEntity<String>("ok", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	
	}
}
