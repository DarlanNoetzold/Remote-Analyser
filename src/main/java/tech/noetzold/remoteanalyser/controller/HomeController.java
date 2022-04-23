package tech.noetzold.remoteanalyser.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tech.noetzold.remoteanalyser.model.Alerta;
import tech.noetzold.remoteanalyser.service.AlertaService;
import tech.noetzold.remoteanalyser.service.LoginAppService;
import tech.noetzold.remoteanalyser.util.LoginApiService;


@Controller@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private AlertaService alertaService;

	@Autowired
	private LoginAppService loginService;

	@Autowired
	private LoginApiService loginProp;

	@GetMapping
	public String getAllPages(Model model){
		return home(model, 1);
	}

	@GetMapping("/remover/{id}")
	public String remover(Model model, @PathVariable("id") Long id) {
		alertaService.removeAlerta(loginProp.getTokenBearer(loginService.getToken(loginProp)), id);
		getAllPages(model);
		return "home";
	}

	@GetMapping("/page/{pageNumber}")
	public String home(Model model, @PathVariable("pageNumber") int currentPage) {
		Pageable pageable = PageRequest.of(currentPage-1,5);
		Page<Alerta> alertas = alertaService.buscaAlertas(loginProp.getTokenBearer(loginService.getToken(loginProp)), pageable);
		int totalPages = alertas.getTotalPages();
		long totalItems = alertas.getTotalElements();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("alertas", alertas.getContent());

		return "home";
	}
}