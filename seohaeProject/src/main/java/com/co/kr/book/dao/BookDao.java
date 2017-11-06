package com.co.kr.book.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.kr.book.vo.BookVo;
import com.co.kr.common.dao.AbstractDAO;

@Repository
public class BookDao extends AbstractDAO{

	public void insertBook(BookVo bookVo) {
		insert("bookSql.insertBook", bookVo);
	}

	public List<BookVo> bookList(BookVo bookVo) {
		return selectList("bookSql.bookList", bookVo);
	}
	
	public BookVo bookDetail(BookVo bookVo) {
		return (BookVo)selectOne("bookSql.bookDetail", bookVo);
	}

	public void bookUpdateSave(BookVo bookVo) {
		update("bookSql.bookUpdateSave", bookVo);
	}
	
	public String fileInfo(int pdNo) {
		return (String)selectOne("bookSql.fileInfo",pdNo);
	}
	
	public void bookDelete(int pdNo) {
		delete("bookSql.bookDelete", pdNo);
	}
}
