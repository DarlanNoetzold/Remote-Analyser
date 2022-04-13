package tech.noetzold.remoteanalyser.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.remoteanalyser.model.Alerta;

import java.util.List;

@Service
@FeignClient(url= "https://spyware-api.herokuapp.com" , name = "spyware")
public interface AlertaService{

    @GetMapping("/alerta")
    Page<Alerta> buscaAlertas(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, Pageable pageable);

    @GetMapping("/alerta/pcId/{pcId}")
    List<Alerta> buscaAlertasPcId(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable("pcId") String pcId);

    @GetMapping("/alerta/remover/{id}")
    void removeAlerta(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable("id") Long id);
}
