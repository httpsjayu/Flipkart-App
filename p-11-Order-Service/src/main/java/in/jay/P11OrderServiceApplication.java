package in.jay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class P11OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(P11OrderServiceApplication.class, args);
	}

}
