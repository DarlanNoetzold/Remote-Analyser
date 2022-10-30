package tech.noetzold.remoteanalyser.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.noetzold.remoteanalyser.util.LoginApiService;

@Service
@FeignClient(url= "https://spyware-api-production.up.railway.app" , name = "spywareLogin")
public interface LoginAppService {

    @PostMapping("/login")
    String getToken(@RequestBody LoginApiService login);
}
