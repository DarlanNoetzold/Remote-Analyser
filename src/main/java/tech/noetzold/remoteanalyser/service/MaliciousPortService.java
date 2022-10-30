package tech.noetzold.remoteanalyser.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.remoteanalyser.model.MaliciousPort;

import java.util.List;

@Service
@FeignClient(url= "https://spyware-api-production.up.railway.app" , name = "spyware")
public interface MaliciousPortService {

    @PostMapping("/port/save")
    void saveMaliciousPort(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestBody MaliciousPort maliciousPort);
    @GetMapping("/port/getAll")
    List<MaliciousPort> buscaMaliciousPort(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);

    @DeleteMapping("/port/remove/{id}")
    void removeMaliciousPort(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable("id") Long id);
}
