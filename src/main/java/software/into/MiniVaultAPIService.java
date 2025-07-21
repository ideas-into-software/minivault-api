package software.into;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * MiniVault API Service
 *
 * @author Michael H. Siemaszko (mhs@into.software)
 */
@Service
public class MiniVaultAPIService {

    private final ChatClient chatClient;

    @Autowired
    MiniVaultAPIService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .call()
                .content();
    }

    public Flux<String> chatAsync(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .stream()
                .content();
    }
}