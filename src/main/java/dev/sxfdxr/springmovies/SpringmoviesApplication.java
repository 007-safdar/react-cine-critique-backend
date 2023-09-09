package dev.sxfdxr.springmovies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringmoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmoviesApplication.class, args);
	}

}
