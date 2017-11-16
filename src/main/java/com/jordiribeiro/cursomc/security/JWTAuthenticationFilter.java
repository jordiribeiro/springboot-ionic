package com.jordiribeiro.cursomc.security;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.couchbase.client.java.error.AuthenticatorException;
import com.jordiribeiro.cursomc.dto.CredenciaisDTO;
import com.jordiribeiro.cursomc.security.utils.JWTUtil;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	
	private JWTUtil jWTUtil;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jWTUtil) {
		this.authenticationManager = authenticationManager;
		this.jWTUtil = jWTUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) throws AuthenticatorException{
		try {
			CredenciaisDTO creds = new ObjectMapper()
	                .readValue(req.getInputStream(), CredenciaisDTO.class);
	
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());
	        
	        Authentication auth = authenticationManager.authenticate(authToken);
	        return auth;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest req,HttpServletResponse res,FilterChain chain,Authentication auth) throws IOException,ServerException {
		String username = ((UserSS) auth.getPrincipal()).getUsername();
        String token = jWTUtil.generateToken(username);
        res.addHeader("Authorization", "Bearer " + token);
	}
	
}
