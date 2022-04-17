package tech.noetzold.remoteanalyser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.noetzold.remoteanalyser.model.UserImp;
import tech.noetzold.remoteanalyser.repository.UserRepository;
import tech.noetzold.remoteanalyser.util.LoginApiService;

@EnableFeignClients
@SpringBootApplication()
@EnableConfigurationProperties(LoginApiService.class)
public class RemoteanalyserApplication {
	public static void main(String[] args) {
		SpringApplication.run(RemoteanalyserApplication.class, args);
	}
}
