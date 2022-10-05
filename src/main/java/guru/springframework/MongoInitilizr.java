package guru.springframework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Profile("mongo")
@Configuration
public class MongoInitilizr {

    @Value("classpath:data-mongo.json")
    private Resource jsonFile;

    @Bean
    @Autowired
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator(ObjectMapper objectMapper){
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setMapper(objectMapper);
        factory.setResources(new Resource[]{jsonFile});
        return factory;
    }
}
