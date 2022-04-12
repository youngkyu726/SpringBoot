package com.yk726.myweb.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class PageVO {

	//페이지네이션을 그리는 클래스
	private int start; //첫 페이지번호
	private int end; //마지막페이지번호
	private boolean prev; //이전버튼
	private boolean next; //다음버튼
	
	private int page; //조회하는 페이지 번호
	private int amount; //데이터 개수
	private int total; //전체게시글 수
	private int realEnd; //실제끝번호

	private Criteria cri;
	
	private List<Integer> pageList; //타임리프에서는 향상된 for문을 제공하기 때문에 페이지번호목록을 리스트에 저장
	
	//생성자 - pageVO는 생성될 때, Criteria와 전체게시글 수를 받음.
	public PageVO(Criteria cri, int total) {
		//페이지번호, 데이터개수, 총게시글 수 초기화
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
		
		//1. 끝페이지 개선
		//공식 = (int)Math.ceil(조회하는페이지/ 페이지네이션 개수) * 페이지네이션 개수
		this.end = (int)Math.ceil(this.page / 10.0) * 10;
		//2. 첫 페이지 계산
		//공식 = 끝페이지 - 페이지네이션 개수 + 1
		this.start = this.end - 10 + 1;
		//3. 실제 끝페이지 번호계산
		//공식 = (int)Math.ceil(전체게시글 수/ 데이터 개수)
		this.realEnd = (int)Math.ceil(this.total / (double)this.amount);
		//4. 실제 끝번호를 다시 결정
		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		
		//5. 이전버튼 활성화 여부
		//시작버튼이 활성화 되는 경우는 11번부터
		this.prev = this.start > 1;
		
		//6. 다음버튼 활성화 여부 (조건 = 4번의 계산과 반대)
		this.next = this.realEnd > this.end;
		
		//7. pageList처리
//		for(int i = this.start; i <= this.end; i++) {
//			this.pageList.add(i);
//		}
		this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
	
	}
}
