package com.co.kr.review.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.book.vo.BookVo;
import com.co.kr.review.dao.ReviewDao;
import com.co.kr.review.service.ReviewService;
import com.co.kr.review.vo.ReviewVo;


@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public void insertReview(ReviewVo reviewVo) {
		reviewDao.insertReview(reviewVo);
	}

	@Override
	public int getReviewCount(ReviewVo reviewVo) {
		return reviewDao.getReviewCount(reviewVo);
	}

	@Override
	public List<ReviewVo> review(ReviewVo reviewVo) {
		return reviewDao.review(reviewVo);
	}

	@Override
	public int reviewTotalCount(int no) {
		return reviewDao.reviewTotalCount(no);
	}

	@Override
	public BookVo bookDetail(ReviewVo reviewVo) {
		return reviewDao.bookDetail(reviewVo);
	}
}
