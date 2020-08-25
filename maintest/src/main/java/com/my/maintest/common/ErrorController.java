package com.my.maintest.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

	// 허가되지 않은 접근 에러 페이지
	@GetMapping("/unauthorizedAccess")
	public String unauthorizedAccess() {

		return "/error/unauthorizedAccess";
	}

}
