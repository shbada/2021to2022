package com.co.kr.book.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.kr.book.dao.BookDao;
import com.co.kr.book.service.BookService;
import com.co.kr.book.vo.BookVo;


@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDao bookDao;

	@Override
	public void insertBook(BookVo bookVo) {
		bookDao.insertBook(bookVo);
	}

	@Override
	public Map<String, Object> bookList(BookVo bookVo) {
		List<BookVo> list = bookDao.bookList(bookVo); 
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		map.put("list", list); 
		
		return map;
	}

	@Override
	public BookVo bookDetail(BookVo bookVo) {
		return bookDao.bookDetail(bookVo);
	}

	@Override
	public void bookUpdateSave(BookVo bookVo) {
		bookDao.bookUpdateSave(bookVo);
	}
	
	@Override
	public String fileInfo(int pdNo) {
		return bookDao.fileInfo(pdNo);
	}
	
	@Override
	public void bookDelete(int pdNo) {
		bookDao.bookDelete(pdNo);
	}
	
	
}
