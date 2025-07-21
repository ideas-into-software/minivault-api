package software.into;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * Tests {@link software.into.MiniVaultAPIApplication}
 *
 * @author Michael H. Siemaszko (mhs@into.software)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "60s")
@TestMethodOrder(OrderAnnotation.class)
public class MiniVaultAPIApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGenerate() {
        webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/generate").build())
                .body(Mono.just(new MiniVaultAPIInput("Hello world!")),
                        MiniVaultAPIInput.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testGenerateAsync() {
        webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/generateAsync").build())
                .body(Mono.just(new MiniVaultAPIInput("Hello world!")),
                        MiniVaultAPIInput.class)
                .exchange()
                .expectStatus().isOk();
    }
}
