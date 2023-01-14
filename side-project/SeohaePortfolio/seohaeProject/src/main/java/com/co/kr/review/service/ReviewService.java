package com.co.kr.review.service;

import java.util.List;

import com.co.kr.book.vo.BookVo;
import com.co.kr.review.vo.ReviewVo;

public interface ReviewService {
	public void insertReview(ReviewVo reviewVo);

	public int getReviewCount(ReviewVo reviewVo);

	public List<ReviewVo> review(ReviewVo reviewVo);

	public int reviewTotalCount(int no);

	public BookVo bookDetail(ReviewVo reviewVo);
}
