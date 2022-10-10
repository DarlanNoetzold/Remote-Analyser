package tech.noetzold.remoteanalyser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.noetzold.remoteanalyser.model.MaliciousPort;
import tech.noetzold.remoteanalyser.service.MaliciousPortService;
import tech.noetzold.remoteanalyser.service.LoginAppService;
import tech.noetzold.remoteanalyser.util.LoginApiService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping()
public class MaliciousPortController {

    @Autowired
    private MaliciousPortService maliciousPortService;

    @Autowired
    private LoginAppService loginService;

    @Autowired
    private LoginApiService loginProp;

    @GetMapping("/maliciousPort")
    public ModelAndView maliciousPort() {
        ModelAndView modelAndView = new ModelAndView("/maliciousPort");
        List<MaliciousPort> maliciousPorts = maliciousPortService.buscaMaliciousPort(loginProp.getTokenBearer(loginService.getToken(loginProp)));
        modelAndView.addObject("maliciousPorts", maliciousPorts);

        return modelAndView;
    }

    @GetMapping("/maliciousPort/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        maliciousPortService.removeMaliciousPort(loginProp.getTokenBearer(loginService.getToken(loginProp)), id);
        return maliciousPort();
    }

    @PostMapping("/maliciousPort/save")
    public ModelAndView save(@Valid MaliciousPort maliciousPort){
        maliciousPortService.saveMaliciousPort(loginProp.getTokenBearer(loginService.getToken(loginProp)), maliciousPort);
        return maliciousPort();
    }
}
