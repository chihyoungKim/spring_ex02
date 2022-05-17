package com.wdkim.mapper;

import com.wdkim.domain.AuthVO;
import com.wdkim.domain.MemberVO;

public interface MemberMapper {
	MemberVO read(String userid);
	
	int insertMember(MemberVO vo);
	
	int insertAuth(AuthVO vo);
}
