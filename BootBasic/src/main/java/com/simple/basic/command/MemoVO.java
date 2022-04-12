package com.simple.basic.command;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoVO {

	//wrapper타입으로
	private Integer mno;
	
	@Pattern(regexp ="[a-zA-Z가-힣0-9]{5,}", message = "내용은 5자 이상")
	private String memo;
	
	@Pattern(regexp ="[0-9]{3}-[0-9]{4}-[0-9]{4}", message = "전화번호 형식이어야 합니다")
	private String phone;
	
	@Pattern(regexp ="[0-9]{4}", message = "비밀번호는 숫자 4자리")
	private String pw;
	
	private String secret;
	
	
}
