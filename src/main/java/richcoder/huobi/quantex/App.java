package richcoder.huobi.quantex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author richcoder
 */
@SpringBootApplication
@EnableScheduling
@Slf4j
public class App {
    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).run(args);
        log.info("App started ok!!!");
    }
}