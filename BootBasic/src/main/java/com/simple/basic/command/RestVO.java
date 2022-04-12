package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestVO {
	
	private Integer num;
	private String name;
	private String id;
	
//	private String name;
//	private String content;
//	private String date;
}
