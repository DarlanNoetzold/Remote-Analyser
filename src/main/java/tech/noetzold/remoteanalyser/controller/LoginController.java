package tech.noetzold.remoteanalyser.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {
	@GetMapping
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping
	@RequestMapping("/login-success")
	public RedirectView successLogin(@AuthenticationPrincipal UserDetails userDetails) {
		boolean isAdmin = userDetails.getAuthorities().stream()
				.anyMatch(authority -> authority.equals(new SimpleGrantedAuthority("ROLE_admin")));

		if (isAdmin) {
			return new RedirectView("/home");
		} else {
			return new RedirectView("/user");
		}
	}

	@GetMapping
	@RequestMapping("/login-error")
	public ModelAndView loginError() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("loginError", "Senha ou usuário inválido!");
		return mav;
	}
}
