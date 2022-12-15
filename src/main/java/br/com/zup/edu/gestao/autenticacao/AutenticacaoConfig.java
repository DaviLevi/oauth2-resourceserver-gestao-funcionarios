package br.com.zup.edu.gestao.autenticacao;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AutenticacaoConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/api/funcionarios", "/api/funcionarios/{\\d+}").hasAnyAuthority("SCOPE_funcionarios:read")
                    .antMatchers(HttpMethod.POST, "/api/funcionarios").hasAnyAuthority("SCOPE_funcionarios:write")
                    .antMatchers(HttpMethod.DELETE, "/api/funcionarios/{\\d+}").hasAnyAuthority("SCOPE_funcionarios:write")
                .anyRequest()
                    .authenticated()
                .and()
            .oauth2ResourceServer()
                .jwt();
    }


}
