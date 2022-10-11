package com.krugger.challenge.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	@Autowired
	JWTService jwtService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		setAuthenticationManager(authenticationManager);
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			String content = IOUtils.toString(request.getReader());
			JSONParser parser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
			JSONObject jsonObject = (JSONObject)parser.parse(content);
			LoginRequest loginRequest = new LoginRequest(jsonObject.getAsString("username"), jsonObject.getAsString("password"));
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
			this.setDetails(request, usernamePasswordAuthenticationToken);
			Authentication authentication = this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
			return authentication;
		} catch (Exception e ) {
			throw new IllegalArgumentException("RequestBody could not be read");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication authResult) throws IOException {
		String token = jwtService.create(authResult);
		response.addHeader(JWTService.HEADER_STRING, JWTService.TOKEN_PREFIX + token);
		Map<String, Object> body = new HashMap<>();
		body.put("token", token);
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
											  AuthenticationException failed) throws IOException {
		Map<String, Object> body = new HashMap<>();
		body.put("mensaje", "Credenciales incorrectas");
		body.put("error", failed.getMessage());
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	static class LoginRequest {
		private String username;
		private String password;
	}
}
