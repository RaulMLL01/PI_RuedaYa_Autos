package edu.dwes.PI_Raul_Lara_Back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import edu.dwes.PI_Raul_Lara_Back.service.implementations.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private PasswordEncoder encoder;

    // -------------------------------------------------------------------------
    // 1. JERARQUÍA DE ROLES
    // -------------------------------------------------------------------------
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("""
                    ROLE_ADMIN > ROLE_OPERADOR
                    ROLE_OPERADOR > ROLE_USUARIO
                """);
        return hierarchy;
    }

    // -------------------------------------------------------------------------
    // 2. AuthoritiesMapper
    // -------------------------------------------------------------------------
    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper(RoleHierarchy roleHierarchy) {
        return new RoleHierarchyAuthoritiesMapper(roleHierarchy);
    }

    // -------------------------------------------------------------------------
    // 3. AuthenticationProvider
    // -------------------------------------------------------------------------
    @Bean
    public AuthenticationProvider authenticationProvider(GrantedAuthoritiesMapper authoritiesMapper) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(encoder);
        provider.setAuthoritiesMapper(authoritiesMapper);
        return provider;
    }

    // -------------------------------------------------------------------------
    // 4. AuthenticationManager
    // -------------------------------------------------------------------------
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new ProviderManager(authenticationProvider);
    }

    // -------------------------------------------------------------------------
    // 5. SecurityFilterChain
    // -------------------------------------------------------------------------
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider,
            JwtAuthenticationFilter jwtAuthenticationFilter // <-- SE INYECTA AQUÍ
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/", "/home", "/bienvenida", "/info/**", "/auth/**", "/mail/**", "/uploads/**")
                        .permitAll()

                        .requestMatchers(HttpMethod.GET, "/anuncios/**").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.POST, "/anuncios/**").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.PUT, "/anuncios/**").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.DELETE, "/anuncios/**").hasRole("USUARIO")

                        .requestMatchers(HttpMethod.POST, "/vehiculos/**").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.DELETE, "/vehiculos/**").hasRole("USUARIO")

                        .requestMatchers("/mensajes/**").hasRole("USUARIO")

                        .requestMatchers(HttpMethod.GET, "/vehiculos/**").hasRole("OPERADOR")
                        .requestMatchers(HttpMethod.PUT, "/vehiculos/**").hasRole("OPERADOR")
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasRole("OPERADOR")
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.POST, "/transacciones").hasRole("USUARIO")
                        .requestMatchers("/usuarios/email/**").hasRole("USUARIO")
                        .requestMatchers("/usuarios/**").hasRole("OPERADOR")

                        .requestMatchers("/roles/**").hasRole("ADMIN")
                        .requestMatchers("/transacciones/**").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // -------------------------------------------------------------------------
    // 6. CORS
    // -------------------------------------------------------------------------
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
