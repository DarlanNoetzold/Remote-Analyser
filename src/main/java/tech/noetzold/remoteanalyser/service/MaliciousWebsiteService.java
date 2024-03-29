package tech.noetzold.remoteanalyser.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.remoteanalyser.model.MaliciousWebsite;

import java.util.List;

@Service
@FeignClient(url= "http://localhost:8091" , name = "spyware")
public interface MaliciousWebsiteService {

    @PostMapping("/website/save")
    void saveMaliciousWebsite(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestBody MaliciousWebsite maliciousWebsite);

    @GetMapping("/website/getAll")
    List<MaliciousWebsite> buscaMaliciousWebsite(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);

    @DeleteMapping("/website/remove/{id}")
    void removeMaliciousWebsite(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable("id") Long id);
}
