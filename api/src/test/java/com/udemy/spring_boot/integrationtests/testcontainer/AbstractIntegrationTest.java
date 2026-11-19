package com.udemy.spring_boot.integrationtests.testcontainer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.mysql.MySQLContainer;

import java.util.Map;
import java.util.stream.Stream;

// O @ServiceConnection seria uma opção para simplificar a configuração,
// mas este Initializer com Startables permite iniciar múltiplos containers em paralelo,
// poupando tempo.

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static MySQLContainer mySQL = new MySQLContainer("mysql:9.6.0");

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testContainers = new MapPropertySource("testcontainers",
                    createConnectionConfiguration());
            
            environment.getPropertySources().addFirst(testContainers);
        }

        private static Map<String, Object> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", mySQL.getJdbcUrl(),
                    "spring.datasource.username", mySQL.getUsername(),
                    "spring.datasource.password", mySQL.getPassword()
            );
        }

        private static void startContainers() {
            Startables.deepStart(Stream.of(mySQL)).join();
        }
    }
}
