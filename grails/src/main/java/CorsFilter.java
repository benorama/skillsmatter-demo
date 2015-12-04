import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Priority;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Priority(Integer.MIN_VALUE)
public class CorsFilter extends OncePerRequestFilter {

    public CorsFilter() { }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws ServletException, IOException {

        String origin = req.getHeader("Origin");

        boolean options = "OPTIONS".equals(req.getMethod());
        if (options) {
            if (origin == null) return;
            resp.addHeader("Access-Control-Allow-Headers", "origin, authorization, accept, content-type, x-requested-with");
            resp.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS");
            resp.addHeader("Access-Control-Max-Age", "3600");
        }

        resp.addHeader("Access-Control-Allow-Origin", origin == null ? "*" : origin);
        resp.addHeader("Access-Control-Allow-Credentials", "true");

        if (!options) chain.doFilter(req, resp);
    }
}