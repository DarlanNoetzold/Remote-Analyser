package tech.noetzold.remoteanalyser.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.noetzold.remoteanalyser.util.LoginApiService;

@Service
@FeignClient(url= "http://localhost:8091" , name = "spywareLogin")
public interface LoginAppService {

    @PostMapping("/login")
    String getToken(@RequestBody LoginApiService login);
}
