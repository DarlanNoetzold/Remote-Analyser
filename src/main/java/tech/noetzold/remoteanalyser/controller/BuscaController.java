package tech.noetzold.remoteanalyser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.noetzold.remoteanalyser.model.Alerta;
import tech.noetzold.remoteanalyser.service.AlertaService;
import tech.noetzold.remoteanalyser.service.LoginAppService;
import tech.noetzold.remoteanalyser.util.LoginApiService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
public class BuscaController {

    @Autowired
    private AlertaService alertaService;

    @Autowired
    private LoginAppService loginService;

    @Autowired
    private LoginApiService loginProp;

    @PostMapping("/buscaPc")
    public ModelAndView buscaPc(Model model, Principal principal, @RequestParam("pcId") String pcId) {

        ModelAndView modelAndView = new ModelAndView("busca");
        List<Alerta> alertas = alertaService.buscaAlertasPcId(loginProp.getTokenBearer(loginService.getToken(loginProp)), pcId);
        modelAndView.addObject("alertas", alertas);

        return modelAndView;
    }

    @GetMapping("/busca")
    public String getAllPages(Model model){
        return busca(model, 1);
    }

    @GetMapping("/busca/page/{pageNumber}")
    public String busca(Model model, @PathVariable("pageNumber") int currentPage) {
        Pageable pageable = PageRequest.of(currentPage-1,5);
        Page<Alerta> alertas = alertaService.buscaAlertas(loginProp.getTokenBearer(loginService.getToken(loginProp)), pageable);
        int totalPages = alertas.getTotalPages();
        long totalItems = alertas.getTotalElements();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("alertas", alertas.getContent());
        
        return "busca";
    }

    @GetMapping("/user")
    public ModelAndView userAlerts(Model model, Principal principal, @AuthenticationPrincipal UserDetails userDetails) {

        ModelAndView modelAndView = new ModelAndView("user");

        try {
            List<Alerta> alertas = alertaService.buscaAlertasPcId(loginProp.getTokenBearer(loginService.getToken(loginProp)), userDetails.getUsername());
            modelAndView.addObject("alertas", alertas);
        }catch (Exception e){
            modelAndView.addObject("alertas", new ArrayList<Alerta>());
            modelAndView.addObject("errorMessage", "Nenhum alerta encontrado.");
            return modelAndView;
        }

        return modelAndView;
    }
}
