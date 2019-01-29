package nl.hu.tosad.webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication()
@EntityScan("nl.hu.tosad.domain")
public class TosadApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TosadApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TosadApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Application started!");
    }
}

