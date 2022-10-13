package tech.noetzold.remoteanalyser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.noetzold.remoteanalyser.model.BadLanguage;
import tech.noetzold.remoteanalyser.service.LoginAppService;
import tech.noetzold.remoteanalyser.util.LoginApiService;
import tech.noetzold.remoteanalyser.service.BadLanguageService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping()
public class BadLanguageController {

    @Autowired
    private BadLanguageService badLanguageService;

    @Autowired
    private LoginAppService loginService;

    @Autowired
    private LoginApiService loginProp;

    @GetMapping("/badLanguage")
    public ModelAndView badLanguage() {
        ModelAndView modelAndView = new ModelAndView("/badLanguage");
        List<BadLanguage> badLanguages = badLanguageService.buscaBadLanguage(loginProp.getTokenBearer(loginService.getToken(loginProp)));
        modelAndView.addObject("badLanguages", badLanguages);

        return modelAndView;
    }

    @GetMapping("/badLanguage/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        badLanguageService.removeBadLanguage(loginProp.getTokenBearer(loginService.getToken(loginProp)), id);
        return badLanguage();
    }

    @PostMapping("/badLanguage/save")
    public ModelAndView save(@Valid BadLanguage badLanguage){
        badLanguageService.saveBadLanguage(loginProp.getTokenBearer(loginService.getToken(loginProp)), badLanguage);
        return badLanguage();
    }


}
