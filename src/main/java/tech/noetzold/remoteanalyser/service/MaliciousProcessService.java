package tech.noetzold.remoteanalyser.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.remoteanalyser.model.MaliciousProcess;

import java.util.List;

@Service
@FeignClient(url= "https://spyware-api.herokuapp.com" , name = "spyware")
public interface MaliciousProcessService {

    @PostMapping("/process/save")
    void saveMaliciousProcess(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestBody MaliciousProcess maliciousProcess);

    @GetMapping("/process/getAll")
    List<MaliciousProcess> buscaMaliciousProcess(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);

    @DeleteMapping("/process/remove/{id}")
    void removeMaliciousProcess(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable("id") Long id);
}
