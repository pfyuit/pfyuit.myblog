package com.pfyuit.myblog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pfyuit.myblog.dto.LoginDto;

@Transactional
@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView save(LoginDto data) {
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(data.getUsername(), data.getPassword());
			subject.login(token);
		} catch (AuthenticationException e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/authen.html?messages=Authentication failed。Pease try again");
			return mav;
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index.html");
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index.html");
		return mav;
	}

}
