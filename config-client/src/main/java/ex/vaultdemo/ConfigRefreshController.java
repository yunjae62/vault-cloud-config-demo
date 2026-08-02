package ex.vaultdemo;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigRefreshController {

    @Value("${message}")
    private String message;

    @Value("${foo}")
    private String foo;

    @GetMapping("/config")
    Map<String, String> config() {
        return Map.of("message", message, "foo", foo);
    }
}
