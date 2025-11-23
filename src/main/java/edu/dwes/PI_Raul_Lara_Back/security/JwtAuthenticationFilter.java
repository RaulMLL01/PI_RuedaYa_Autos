package edu.dwes.PI_Raul_Lara_Back.security;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.dwes.PI_Raul_Lara_Back.exceptions.NonExistentException;
import edu.dwes.PI_Raul_Lara_Back.model.entities.Usuario;
import edu.dwes.PI_Raul_Lara_Back.service.IUsuarioService;
import edu.dwes.PI_Raul_Lara_Back.service.security.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final IUsuarioService usuarioService;
    private final GrantedAuthoritiesMapper authoritiesMapper;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            IUsuarioService usuarioService,
            GrantedAuthoritiesMapper authoritiesMapper) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
        this.authoritiesMapper = authoritiesMapper;
    }

    // ----------------------------------------------------
    // 1. Rutas que NO deben pasar por el filtro JWT
    // ----------------------------------------------------
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return path.startsWith("/auth/login")
                || path.startsWith("/auth/registro")
                || path.startsWith("/home")
                || path.startsWith("/info")
                || path.equals("/")
                || path.equals("/error");
    }

    // ----------------------------------------------------
    // 2. Autenticación mediante JWT
    // ----------------------------------------------------
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);
            String email = jwtService.extractUsername(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                Usuario usuario;
                try {
                    usuario = usuarioService.findByEmail(email);

                    if (usuario != null && jwtService.isTokenValid(token, usuario)) {

                        SimpleGrantedAuthority baseAuthority = new SimpleGrantedAuthority(
                                "ROLE_" + usuario.getRol().getNombre());

                        // aplicar jerarquía
                        var authorities = authoritiesMapper.mapAuthorities(List.of(baseAuthority));

                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                usuario,
                                null,
                                authorities);

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } catch (NonExistentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }

        filterChain.doFilter(request, response);
    }
}
