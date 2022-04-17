package tech.noetzold.remoteanalyser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.noetzold.remoteanalyser.model.UserImp;
import tech.noetzold.remoteanalyser.repository.UserRepository;

@Controller
public class LoginController {
	@GetMapping
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
