package com.wdkim.domain;

import java.util.Arrays;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Criteria {
	private int pageNum = 1;
	private int amount = 10;
	private String type; // T C W
//	private String[] typeArr;
	private String keyword;
	
	
	public String getParams() {
		return UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", pageNum)
				.queryParam("amount", amount)
				.queryParam("type", type)
				.queryParam("keyword", keyword)
				.toUriString();
	}
	public String getParamsWithOutPageNum() {
		return UriComponentsBuilder.fromPath("")
				.queryParam("amount", amount)
				.queryParam("type", type)
				.queryParam("keyword", keyword)
				.toUriString();
	}
	public String[] getTypeArr() { // 다중검색을 위함
		return type == null ? new String[] {} : type.split("");
	}
	
//	public String getType() {
//		return typeArr == null ? null : String.join("", typeArr);
//	}
}
