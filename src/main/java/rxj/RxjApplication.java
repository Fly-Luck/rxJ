package rxj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ThreadPoolConfig.class)
public class RxjApplication {
    public static void main( String[] args ) {
    	SpringApplication.run(RxjApplication.class, args);
    }
}
