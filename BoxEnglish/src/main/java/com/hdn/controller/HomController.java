package com.hdn.controller;

import javax.servlet.http.HttpSession;

import com.hdn.cons.Cons;
import com.hdn.daoimp.ReviewImpl;
import com.hdn.entity.ReviewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdn.entity.CategoryEntity;
import com.hdn.entity.UserEntity;
import com.hdn.service.UserService;
import com.sun.jdi.Method;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomController {

	@Autowired
	private UserService userService;
	@Autowired
	private ReviewImpl reviewImpl;

	@GetMapping
	public String Home(HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "home";
		} else {
			return "redirect:/login";
		}

	}

	@GetMapping(value = "/practice-voca")
	public String PracticeVoca() {
		return "practice-voca";
	}

	@GetMapping(value = "/list-voca")
	public String ListVoca() {
		return "list-voca";
	}

	@GetMapping(value = "/admin")
	public String Admin() {
		return "admin/index";
	}

	@GetMapping("list-note")
	public String listNote(HttpSession httpSession) {
		return "redirect:note/";
	}

	@GetMapping(value = "/login")
	public String Login() {
		return "login";
	}

	@GetMapping(value = "/register")
	public String Register() {
		return "register";
	}
	
	@GetMapping("edit-info-user")
	public String editInfoUser(ModelMap model,@SessionAttribute("user") UserEntity user) {
		model.addAttribute("user", user);
		return "edit-info-user";
	}
	
	@GetMapping(value = "/list-topic")
	public String Topic() {
		return "list-topic";
	}
	@GetMapping(value = "/list-box")
	public ModelAndView Box() {
		ModelAndView mav = new ModelAndView("list-box");
		int numOfBox1=0,numOfBox2=0,numOfBox3=0,numOfBox4=0,numOfBox5=0,numOfBox6=0;
		List<ReviewEntity> reviewEntitiesBox1 = reviewImpl.getReviewsByUserIdAndLevel(Cons.USER_ID,1);
		List<ReviewEntity> reviewEntitiesBox2 = reviewImpl.getReviewsByUserIdAndLevel(Cons.USER_ID,2);
		List<ReviewEntity> reviewEntitiesBox3 = reviewImpl.getReviewsByUserIdAndLevel(Cons.USER_ID,3);
		List<ReviewEntity> reviewEntitiesBox4 = reviewImpl.getReviewsByUserIdAndLevel(Cons.USER_ID,4);
		List<ReviewEntity> reviewEntitiesBox5 = reviewImpl.getReviewsByUserIdAndLevel(Cons.USER_ID,5);
		List<ReviewEntity> reviewEntitiesBox6 = reviewImpl.getReviewsByUserIdAndLevel(Cons.USER_ID,6);
		if (reviewEntitiesBox1 != null) {
			numOfBox1 = reviewEntitiesBox1.size();
		}
		if (reviewEntitiesBox2 != null) {
			numOfBox2 = reviewEntitiesBox2.size();
		}
		if (reviewEntitiesBox3 != null) {
			numOfBox3 = reviewEntitiesBox3.size();
		}
		if (reviewEntitiesBox4 != null) {
			numOfBox4 = reviewEntitiesBox4.size();
		}
		if (reviewEntitiesBox5 != null) {
			numOfBox5 = reviewEntitiesBox5.size();
		}
		if (reviewEntitiesBox6 != null) {
			numOfBox6 = reviewEntitiesBox6.size();
		}
		mav.addObject("numOfBox1",numOfBox1);
		mav.addObject("numOfBox2",numOfBox2);
		mav.addObject("numOfBox3",numOfBox3);
		mav.addObject("numOfBox4",numOfBox4);
		mav.addObject("numOfBox5",numOfBox5);
		mav.addObject("numOfBox6",numOfBox6);
		return mav;
	}
	@GetMapping(value = "/box-detail")
	public String BoxDetail() {
		return "box-detail";
	}

}
