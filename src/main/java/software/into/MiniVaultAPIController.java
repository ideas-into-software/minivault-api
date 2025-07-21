package software.into;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * MiniVault API Controller
 *
 * @author Michael H. Siemaszko (mhs@into.software)
 */
@RestController
public class MiniVaultAPIController {

    private final MiniVaultAPIService miniVaultAPIService;

    MiniVaultAPIController(MiniVaultAPIService miniVaultAPIService) {
        this.miniVaultAPIService = miniVaultAPIService;
    }

    @PostMapping("/generate")
    public Map<String, String> generate(@RequestBody MiniVaultAPIInput input) {
        return Map.of("response", miniVaultAPIService.chat(input.prompt()));
    }

    @PostMapping("/generateAsync")
    public Flux<String> generateAsync(@RequestBody MiniVaultAPIInput input) {
        return miniVaultAPIService.chatAsync(input.prompt());
    }
}