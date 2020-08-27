package com.applaudo.movies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    @Value("${movie.apiVersion}")
    private String apiVersion;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint(new OAuth2Exception())
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()
//                .antMatchers().denyAll()
                .antMatchers("/api/" + apiVersion + "/purchases/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/" + apiVersion + "/rentals/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/" + apiVersion + "/movies/**").permitAll()
                .antMatchers("/api/" + apiVersion + "/movies/**").authenticated()
                .antMatchers("/h2-console/**").permitAll();
        // .permitAll(); // allows us to test our path using our fontend app without auth

        // used only to render our GUI h2-console, DO NOT USE THIS IN PRODUCTION!
        http.headers().frameOptions().disable();

    }
}