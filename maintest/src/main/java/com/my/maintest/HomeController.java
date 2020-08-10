package com.my.maintest;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my.maintest.board.vo.BoardVO;
//import com.my.maintest.board.vo.TestVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);



	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
//	@GetMapping("/test")
//	public ModelAndView toTest() {
//		
//		
//		return new ModelAndView("/board/boardWriteFrm1", "TestVO", new TestVO());
//	}

	
//	@PostMapping("/testDO")
//	public String toTestto(
//			//@RequestParam("btitle") String btitle,
//			@ModelAttribute TestVO testVO ,
//			RedirectAttributes redirectAttributes
//			
//			) {
//		
//		System.out.println(testVO.toString());
//		//System.out.println(boardVO.toString());
//		redirectAttributes.addAttribute("testVO", testVO);
//		
//		return "redirect:/test";
//		
//	}
}
