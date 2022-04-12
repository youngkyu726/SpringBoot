package com.yk726.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicVO {

	private Integer topic_bno;
	private String topic_regdate;
	private String topic_id;
	private String topic_title;
	private String topic_content;
}
