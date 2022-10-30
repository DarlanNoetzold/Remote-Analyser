package tech.noetzold.remoteanalyser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.noetzold.remoteanalyser.model.MaliciousWebsite;
import tech.noetzold.remoteanalyser.service.LoginAppService;
import tech.noetzold.remoteanalyser.service.MaliciousWebsiteService;
import tech.noetzold.remoteanalyser.util.LoginApiService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping()
public class MaliciousWebsiteController {
    @Autowired
    private MaliciousWebsiteService maliciousWebsiteService;

    @Autowired
    private LoginAppService loginService;

    @Autowired
    private LoginApiService loginProp;

    @GetMapping("/maliciousWebsite")
    public ModelAndView maliciousWebsite() {
        ModelAndView modelAndView = new ModelAndView("maliciousWebsite");
        List<MaliciousWebsite> maliciousWebsites = maliciousWebsiteService.buscaMaliciousWebsite(loginProp.getTokenBearer(loginService.getToken(loginProp)));
        modelAndView.addObject("maliciousWebsites", maliciousWebsites);

        return modelAndView;
    }

    @GetMapping("/maliciousWebsite/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        maliciousWebsiteService.removeMaliciousWebsite(loginProp.getTokenBearer(loginService.getToken(loginProp)), id);
        return maliciousWebsite();
    }

    @PostMapping("/maliciousWebsite/save")
    public ModelAndView save(@Valid MaliciousWebsite maliciousWebsite){
        maliciousWebsiteService.saveMaliciousWebsite(loginProp.getTokenBearer(loginService.getToken(loginProp)), maliciousWebsite);
        return maliciousWebsite();
    }
}
