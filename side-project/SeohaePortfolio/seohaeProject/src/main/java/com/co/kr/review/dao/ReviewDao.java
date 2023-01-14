package com.co.kr.review.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.kr.book.vo.BookVo;
import com.co.kr.common.dao.AbstractDAO;
import com.co.kr.review.vo.ReviewVo;

@Repository
public class ReviewDao extends AbstractDAO{
	
	public void insertReview(ReviewVo reviewVo) {
		insert("reviewSql.insertReview", reviewVo);
	}

	public int getReviewCount(ReviewVo reviewVo) {
		return (int)selectOne("reviewSql.getReviewCount",reviewVo);
	}

	public List<ReviewVo> review(ReviewVo reviewVo) {
		return selectList("reviewSql.review",reviewVo);
	}

	public int reviewTotalCount(int no) {
		return (int) selectOne("reviewSql.reviewTotalCount",no);
	}

	public BookVo bookDetail(ReviewVo reviewVo) {
		return (BookVo) selectOne("reviewSql.bookDetail", reviewVo);
	}
}
