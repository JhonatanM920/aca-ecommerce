package co.com.aca.ventas.e_commerce.config.security;

import co.com.aca.ventas.e_commerce.config.filter.JwtAuthenticationFilter;
import co.com.aca.ventas.e_commerce.util.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .sessionManagement(httpSessionConfigurer -> httpSessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( authConfig ->{

                    authConfig.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST,"/usuarios/crear").permitAll();

                    authConfig.requestMatchers(HttpMethod.PUT,"/usuarios/actualizar").hasAuthority(Permission.ACTUALIZAR_USUARIO.name());

                    authConfig.requestMatchers(HttpMethod.POST,"/productos/crear").hasAuthority(Permission.CREAR_PRODUCTO.name());
                    authConfig.requestMatchers(HttpMethod.PUT,"/productos/actualizar").hasAuthority(Permission.ACTUALIZAR_PRODUCTO.name());
                    authConfig.requestMatchers(HttpMethod.GET,"/productos/listar").hasAuthority(Permission.LISTAR_TODOS_LOS_PRODUCTOS.name());
                    authConfig.requestMatchers(HttpMethod.PUT,"/productos/actualizar-unidades").hasAuthority(Permission.ACTUALIZAR_UNIDADES.name());
                    authConfig.requestMatchers(HttpMethod.GET,"/productos/listar-activos").hasAuthority(Permission.LISTAR_TODOS_PRODUCTOS_ACTIVOS.name());


                    /*authConfig.requestMatchers(HttpMethod.POST,"/locationUsers/creating").hasAuthority(Permission.ADD_LOCATION_ONE_USER.name());
                    authConfig.requestMatchers(HttpMethod.POST,"/users/showProfiles/**").hasAuthority(Permission.SHOW_PROFILE_USER.name());
                    authConfig.requestMatchers(HttpMethod.PUT,"/users/updates").hasAuthority(Permission.UPDATE_ONE_USER.name());

                    authConfig.requestMatchers(HttpMethod.POST,"/barbershops/created").hasAuthority(Permission.CREATE_ONE_BARBERSHOP.name());
                    authConfig.requestMatchers(HttpMethod.PUT,"/barbershops/updated").hasAuthority(Permission.UPDATE_ONE_BARBERSHOP.name());*/


                    authConfig.anyRequest().denyAll();
                });





        return http.build();
    }
}
