package com.wdkim.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper=true) // 조상필드의 것을 보고자 한다면 필수적으로 있어야할 어노테이션.
@NoArgsConstructor
@Getter
@Setter
public class ReplyAttachVO extends AttachFileDTO{
	private Long rno;

	public ReplyAttachVO(String origin, String uuid, String path, boolean image, Long bno) {
		super(origin, uuid, path, image);
		this.rno = rno;
	}
	
	public ReplyAttachVO(AttachFileDTO dto, Long rno) {
		this(dto.getOrigin(), dto.getUuid(), dto.getPath(), dto.isImage(), rno);
	}
	
}
