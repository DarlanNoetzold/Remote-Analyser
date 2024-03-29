package tech.noetzold.remoteanalyser.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.remoteanalyser.model.BadLanguage;

import java.util.List;

@Service
@FeignClient(url= "http://localhost:8091" , name = "spyware")
public interface  BadLanguageService {

    @PostMapping("/language/save")
    void saveBadLanguage(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestBody BadLanguage badLanguage);

    @GetMapping("/language/getAll")
    List<BadLanguage> buscaBadLanguage(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);

    @DeleteMapping("/language/remove/{id}")
    void removeBadLanguage(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable("id") Long id);

}
