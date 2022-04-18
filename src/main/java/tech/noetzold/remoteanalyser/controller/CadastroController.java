package tech.noetzold.remoteanalyser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.noetzold.remoteanalyser.model.UserImp;
import tech.noetzold.remoteanalyser.repository.UserRepository;

@Controller
public class CadastroController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    @RequestMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @PostMapping
    @RequestMapping("/salvarUser")
    public String salvarUser(UserImp user) {
        UserImp userEncode = new UserImp(user.getUsername(), passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEncode);
        return "cadastro";
    }
}
