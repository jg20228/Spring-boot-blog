package com.cos.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.controller.dto.CommonRespDto;
import com.cos.blog.model.Post;
import com.cos.blog.model.User;
import com.cos.blog.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {

	//@RequiredArgsConstructor를 위에 걸면 
	//final에서 오류가 나서 초기화 시켜줘야하는 애들을 생성자 생성함.
	//그러면 자동적으로 AutoWired가 된다.
	private final PostService postService;
	
	//AutoWired 내부적인것
	//private PostService postService;
	//public PostController(PostService postServiceost) {
	//	this.postService = postService;
		//스프링 IoC를 찾아감
	//}
	
	//page를 요청하는거라 주소가 이럼
	@GetMapping("/post/saveForm")
	public String saveForm() {
		return "post/saveForm";
	}
	
	@PostMapping("/post")//JSON으로 받아야해서 @RequestBody!
	public @ResponseBody CommonRespDto<?> postProc(@RequestBody Post requestPost) {
		//userId, title, content
		postService.글쓰기(requestPost);
		//로그인 같은경우는 select로 찾을수도 못찾을수도 있어서 분기를한다. 밑에 것이 여기선 의미가 없긴함
		return new CommonRespDto<String>(1,"글쓰기 성공");
	}
	
	// post관련된 것은 전부 다 인증 필요 한것으로 만든다.
	@GetMapping("/posts")
	public String getPosts(Model model){
		//model은 RequestDispatcher임 view까지 데이터를 끌고간다. -> 그러면 posts로 뿌리기만 하면됨
		model.addAttribute("posts", postService.목록보기());
		return "index";
	}
	
	// /post/안녕 => 오류  @PathVariable이 걸러준다.
	// ?주소  -> 쿼리스트링
	// post/{id} -> 파라메터를 받는 것
	@GetMapping("/post/{id}")
	public String getPost(@PathVariable int id, Model model) {
		//USER랑 조인할려고 DTO 만듬
		model.addAttribute("postDetailRespDto",postService.상세보기(id));
		return "post/detail";
	}
	
	@DeleteMapping("/post/{id}")
	public @ResponseBody CommonRespDto<?> deleteById(@PathVariable int id) {
		postService.삭제하기(id);
		return new CommonRespDto<String>(1, "삭제 결과 성공");
	}
}
