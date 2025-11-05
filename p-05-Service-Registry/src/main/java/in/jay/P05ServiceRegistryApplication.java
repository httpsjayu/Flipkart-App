package in.jay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class P05ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(P05ServiceRegistryApplication.class, args);
	}

}
