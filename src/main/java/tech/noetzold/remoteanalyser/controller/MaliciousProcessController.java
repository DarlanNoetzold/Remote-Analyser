package tech.noetzold.remoteanalyser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tech.noetzold.remoteanalyser.model.MaliciousProcess;
import tech.noetzold.remoteanalyser.service.LoginAppService;
import tech.noetzold.remoteanalyser.service.MaliciousProcessService;
import tech.noetzold.remoteanalyser.util.LoginApiService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping()
public class MaliciousProcessController {
    @Autowired
    private MaliciousProcessService maliciousProcessService;

    @Autowired
    private LoginAppService loginService;

    @Autowired
    private LoginApiService loginProp;

    @GetMapping("/maliciousProcess")
    public ModelAndView maliciousProcess() {
        ModelAndView modelAndView = new ModelAndView("maliciousProcess");
        List<MaliciousProcess> maliciousProcesss = maliciousProcessService.buscaMaliciousProcess(loginProp.getTokenBearer(loginService.getToken(loginProp)));
        modelAndView.addObject("maliciousProcesses", maliciousProcesss);

        return modelAndView;
    }

    @GetMapping("/maliciousProcess/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        maliciousProcessService.removeMaliciousProcess(loginProp.getTokenBearer(loginService.getToken(loginProp)), id);
        return maliciousProcess();
    }

    @PostMapping("/maliciousProcess/save")
    public ModelAndView save(@Valid MaliciousProcess maliciousProcess){
        maliciousProcessService.saveMaliciousProcess(loginProp.getTokenBearer(loginService.getToken(loginProp)), maliciousProcess);
        return maliciousProcess();
    }
}
