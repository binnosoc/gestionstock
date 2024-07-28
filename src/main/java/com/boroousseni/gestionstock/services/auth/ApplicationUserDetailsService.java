//package com.boroousseni.gestionstock.services.auth;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.boroousseni.gestionstock.dto.UserDto;
//import com.boroousseni.gestionstock.models.auth.ExtendedUser;
//import com.boroousseni.gestionstock.services.UserService;
//
//@Service
//public class ApplicationUserDetailsService implements UserDetailsService {
//	@Autowired
//	private UserService service;
//
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		UserDto user = service.findByEmail(email);
//
//		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		
//		user.getRole().forEach(roleElt -> authorities.add(new SimpleGrantedAuthority(roleElt.getName())));
//
//		return (UserDetails) new ExtendedUser(user.getBaseInfo().getEmail(), user.getPassword(), user.getCompany().getId(),
//				authorities);
//	}
//}
