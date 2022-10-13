package tech.noetzold.remoteanalyser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.noetzold.remoteanalyser.model.UserImp;
import tech.noetzold.remoteanalyser.repository.UserRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public ModelAndView salvarUser(@Valid UserImp user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView mav = new ModelAndView("cadastro");
            List<String> msg = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            mav.addObject("msg", msg);
            return mav;
        }
        UserImp userEncode = new UserImp(user.getUsername(), passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEncode);
        ModelAndView mav = new ModelAndView("cadastro");
        mav.addObject("successRegister", "Usuário Cadastrado!");
        return mav;
    }
}
