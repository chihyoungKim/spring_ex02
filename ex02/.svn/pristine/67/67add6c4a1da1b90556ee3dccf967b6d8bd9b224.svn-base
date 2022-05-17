package com.wdkim.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wdkim.mapper.Samplemapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
// 클래스레벨로 걸수도 있다. 인터페이스에 걸면 구현한 모든 클래스에 적용된다.
public class SampleService {
	private Samplemapper mapper;
	
	@Transactional
	public void test(String str) {
		mapper.insert1(str);
		mapper.insert2(str);
	}

	public void test2(String str) {
		mapper.insert1(str);
		mapper.insert2(str);
	}
	
}
