package developer.outofmemory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("developer.outofmemory.dao")
public class OutOfMemoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutOfMemoryApplication.class, args);
    }

}
