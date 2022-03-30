package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder //빌더패턴을 사용가능
public class BuilderVO2 {
	private String name;
	private int age;
	
}
