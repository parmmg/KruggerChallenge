package com.krugger.challenge.auth;

import com.krugger.challenge.entity.User;
import com.krugger.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;

public class KruggerChallengeUserDetails implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user != null) {
			Collection<GrantedAuthority> grantedAuthoritys= new HashSet<>();
			user.getRoles().forEach(role -> grantedAuthoritys.add(new SimpleGrantedAuthority(role.getName())));
			return new org.springframework.security.core.userdetails.User(user.getUserName(),
					user.getPassword(), grantedAuthoritys);
		}
		return null;
	}

}
