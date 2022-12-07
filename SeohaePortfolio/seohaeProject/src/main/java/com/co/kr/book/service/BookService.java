package com.co.kr.book.service;

import java.util.Map;

import com.co.kr.book.vo.BookVo;

public interface BookService {

	void insertBook(BookVo bookVo);

	Map<String, Object> bookList(BookVo bookVo);

	BookVo bookDetail(BookVo bookVo);

	void bookUpdateSave(BookVo bookVo);
	
	String fileInfo(int pdNo);

	void bookDelete(int pdNo);
	
}
