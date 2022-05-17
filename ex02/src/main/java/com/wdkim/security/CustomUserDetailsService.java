package com.wdkim.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wdkim.domain.CustomUser;
import com.wdkim.domain.MemberVO;
import com.wdkim.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@Log4j
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired @Setter
	private MemberMapper mapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn(username);
		MemberVO vo = mapper.read(username);
		return vo == null ? null : new CustomUser(vo);
	}
	
}
