package com.kodilla.library.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
public class JwtUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try{
            UsernameAndPasswordAuthReq auth = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthReq.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    auth.getUsername(),auth.getPassword()
            );
            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;

        }catch (IOException e){
            throw new RuntimeException("You dont have permissions");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String key = "x-hGnUd&$ReFPvW&y!ooZrfBFGsaHpBSg5zr89skiUi84*F2Ehh7zxZa+ew$ok-TPTatXJmV10s@uR|$1Xe&_|YprYAy-0oux_UgVFYUt5&ED6BZ%dMr^1*G6VrcCdG_xTvjKDaU9Nb?TWVHqBCr4R$vaB!#mEOQeOz5c2YjlRPBWbIF$a_zq2uW_q6?V8O6wwrF!S8?9m9cvut^#GwTG1rDrGwCxIcpwvnihIFcwG&Wq?XtOVweRr5PdiylYTV0";
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setExpiration(Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
        response.addHeader("Authorization","Bearer "+token);
        
    }
}
