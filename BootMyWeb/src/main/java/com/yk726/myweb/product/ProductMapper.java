package com.yk726.myweb.product;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.yk726.myweb.command.CategoryVO;
import com.yk726.myweb.command.ProductUploadVO;
import com.yk726.myweb.command.ProductVO;
import com.yk726.myweb.util.Criteria;

@Mapper //필수
public interface ProductMapper {
	
	public int regist(ProductVO vo);//등록
	public int registFile(ProductUploadVO vo); //파일등록
//	public ArrayList<ProductVO> getList(); //목록
	public ArrayList<ProductVO> getList(Criteria cri);//목록
	public int getTotal(Criteria cri); //전체게시글수
	
	public ProductVO getDetail(int prod_id); //상세
	public int update(ProductVO vo); //수정
	public int delete(int prod_id);//삭제

	public ArrayList<CategoryVO> getCategory();//카테고리
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo);//카테고리
	public ArrayList<ProductUploadVO> getDetailImg(int prod_id); //이미지 처리
}
