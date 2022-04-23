package tech.noetzold.remoteanalyser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@GetMapping
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping
	@RequestMapping("/login-error")
	public ModelAndView loginError() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("loginError", "Senha ou usuário inválido!");
		return mav;
	}
}
