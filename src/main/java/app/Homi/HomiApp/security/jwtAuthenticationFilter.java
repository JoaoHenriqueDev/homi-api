package app.Homi.HomiApp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class jwtAuthenticationFilter extends OncePerRequestFilter {

    private final jwtService jwtService;
    private final userDetailsServiceImpl userDetailsService;

    public jwtAuthenticationFilter(
            jwtService jwtService,
            userDetailsServiceImpl userDetailsService
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // 1️⃣ Se não tem token, segue a vida
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2️⃣ Remove "Bearer "
        String token = authHeader.substring(7);

        // 3️⃣ Valida o token
        if (!jwtService.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 4️⃣ Extrai email do token
        String email = jwtService
                .extractClaims(token)
                .get("email", String.class);

        // 5️⃣ Carrega usuário do banco
        userDetailsImpl userDetails = (userDetailsImpl) userDetailsService.loadUserByUsername(email);

        // 6️⃣ Cria autenticação para o Spring
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        // 7️⃣ Coloca no contexto de segurança
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 8️⃣ Continua a requisição
        filterChain.doFilter(request, response);
    }
}
