package com.buse.ai_log_explainer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", "Hello World",
                "author", "Buse"
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
                "status", "running",
                "service", "ai-log-explainer"
        );
    }

    @PostMapping("/explain")
    public Map<String, String> explain(@RequestBody Map<String, String> data) {

        String log = data.get("log");

        if (log == null || log.trim().isEmpty()) {
            return Map.of(
                    "error", "log field is required",
                    "example", "Send JSON like {\"log\":\"NullPointerException at line 23\"}"
            );
        }

        String explanation = generateExplanation(log);

        return Map.of(
                "log", log,
                "explanation", explanation
        );
    }

    private String generateExplanation(String log) {
        String lower = log.toLowerCase();

        if (lower.contains("nullpointerexception")) {
            return "Likely you used an object that is null. Check the line mentioned and see which variable is null.";
        }
        if (lower.contains("indexoutofboundsexception")) {
            return "You accessed an index outside the list/array bounds. Check size and indexes.";
        }
        if (lower.contains("illegalargumentexception")) {
            return "A method received an invalid argument. Check the parameters you pass.";
        }
        if (lower.contains("sql")) {
            return "This looks like a database-related error. Check query syntax, connection, and table/column names.";
        }

        return "I could not recognize the error type. Share the full stack trace for a better explanation.";
    }
}