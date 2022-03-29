package tech.noetzold.remoteanalyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tech.noetzold.remoteanalyser.util.LoginApiService;

@EnableFeignClients
@SpringBootApplication()
@EnableConfigurationProperties(LoginApiService.class)
public class RemoteanalyserApplication {
	public static void main(String[] args) {
		SpringApplication.run(RemoteanalyserApplication.class, args);
	}

}
