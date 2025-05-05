package com.micro.graphs;

import com.micro.graphs.services.GraphService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
class GraphsApplicationTests {

    @Container
    @ServiceConnection
    private static final Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>("neo4j:" + env("NEO4J_VERSION", "5"))
            .withAdminPassword("admin123");

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.neo4j.database", () -> "neo4j");
    }

    @BeforeEach
    void setup(@Autowired Driver driver) {
        try (Session session = driver.session()) {
            session.executeWrite(tx -> {
                tx.run("MATCH (n) DETACH DELETE n");
                tx.run("""
						CREATE (a1:Account {accountNumber: 1})
						CREATE (a2:Account {accountNumber: 2})
						CREATE (a3:Account {accountNumber: 3})
						CREATE (a4:Account {accountNumber: 4})
						CREATE (a1)-[:PERFORMS]->(:TRANSACTION {amount: 1000, currency: "gbp", date: datetime()-duration({days: 3})})-[:BENEFITS_TO]->(a2)
						CREATE (a2)-[:PERFORMS]->(:TRANSACTION {amount: 900, currency: "gbp", date: datetime()-duration({days: 2})})-[:BENEFITS_TO]->(a3)
						CREATE (a3)-[:PERFORMS]->(:TRANSACTION {amount: 810, currency: "gbp", date: datetime()-duration({days: 1})})-[:BENEFITS_TO]->(a4)
						CREATE (a4)-[:PERFORMS]->(:TRANSACTION {amount: 729, currency: "gbp", date: datetime()})-[:BENEFITS_TO]->(a1)
                        """);
                return null;
            });
        }
    }

    @Test
    void findAccountByAccountNumber(@Autowired GraphService graphService) {
        assertThat(graphService.findByAccountNumber(1))
                .extracting(a -> a.getAccountNumber()).isEqualTo(1);
    }

    private static String env(String name, String defaultValue) {
        String value = System.getenv(name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }
}
