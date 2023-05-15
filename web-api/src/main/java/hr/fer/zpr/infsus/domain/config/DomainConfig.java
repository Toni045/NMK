package  hr.fer.zpr.infsus.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("hr.fer.zpr.infsus.domain")
@EnableJpaRepositories("hr.fer.zpr.infsus.infrastructure")
@EnableTransactionManagement
public class DomainConfig {
}
