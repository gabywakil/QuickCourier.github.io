package co.edu.unbosque.tallerpatrones.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final int maxRequests;
    private final long windowMillis;

    private static class Window {
        long windowStart;
        int count;
    }

    private final Map<String, Window> counters = new ConcurrentHashMap<>();

    public RateLimitingFilter(
            @Value("${security.rate-limit.max-requests:100}") int maxRequests,
            @Value("${security.rate-limit.window-millis:60000}") long windowMillis) {
        this.maxRequests = maxRequests;
        this.windowMillis = windowMillis;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String key = resolverClaveRateLimit(request);
        long ahora = Instant.now().toEpochMilli();

        Window w = counters.computeIfAbsent(key, k -> {
            Window nw = new Window();
            nw.windowStart = ahora;
            nw.count = 0;
            return nw;
        });

        synchronized (w) {
            if (ahora - w.windowStart > windowMillis) {
                w.windowStart = ahora;
                w.count = 0;
            }

            if (w.count >= maxRequests) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write("Rate limit excedido");
                return;
            }

            w.count++;
        }

        filterChain.doFilter(request, response);
    }

    private String resolverClaveRateLimit(HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        if (apiKey != null && !apiKey.isBlank()) {
            return "apiKey:" + apiKey;
        }

        String auth = request.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ")) {
            return "jwt:" + auth.substring(7);
        }

        return "ip:" + request.getRemoteAddr();
    }
}
