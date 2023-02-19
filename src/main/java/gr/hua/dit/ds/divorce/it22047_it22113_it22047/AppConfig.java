package gr.hua.dit.ds.divorce.it22047_it22113_it22047;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.data.DivorceService;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.data.DivorceServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public DivorceService divorceService() {
        return new DivorceServiceImpl();
    }
}