package com.co.kr.book.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.co.kr.book.service.BookService;
import com.co.kr.book.vo.BookVo;
import com.co.kr.user.vo.UserVo;

/**
 * BookController
 * 
 * @author 김서해
 * @since 2017. 11. 05.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 05 / seohae / 최초생성)
 *
 * </pre>
 */

@Controller
public class BookController {
	
	private Log log = LogFactory.getLog(BookController.class);
	
	@Autowired
	BookService bookService;
	
	/**
	    * @Method BookList
	    * @Date 2017. 11. 05.
	    * @Writter seohae
	    * @Discript 판매교재 업로드 저장
	    * @Return String
	  */
	
	@RequestMapping(value="/bookList")
	public String itemManage(Model model, @ModelAttribute("BookVo") BookVo bookVo, HttpSession session){
		Map<String, Object> map = bookService.bookList(bookVo); 
		String path = "C:\\seohaeProject\\upload\\";
		
		String userId = (String) session.getAttribute("userId");
		UserVo userVo = new UserVo();
		userVo.setUserId(userId);
		
		model.addAttribute("userVo", userVo);
		model.addAttribute("path", path);
		model.addAttribute("pageVO", bookVo);
		model.addAttribute("list", map.get("list")); 
		
		return "book/bookList";
	}
	
	/**
	    * @Method BookList
	    * @Date 2017. 11. 06.
	    * @Writter seohae
	    * @param 
	    * @Discript 판매교재 업로드 페이지
	    * @Return String
	  */
	@RequestMapping(value="/bookUpload")
	public String itemUpload(){
		return "book/bookUpload";
	}
	
	/**
	    * @Method insertItem
	    * @Date 2017. 11. 06.
	    * @Writter seohae
	    * @param bookVo
	    * @param multipartFile
	    * @Discript 판매교재 목록
	    * @Return String
	  */
	@RequestMapping(value="/insertBookSave", method={RequestMethod.GET, RequestMethod.POST})
	public String insertItem(@ModelAttribute BookVo bookVo, @RequestParam("product_photo")MultipartFile multipartFile)throws Exception{
		
		String filename = "";
        // 첨부파일(상품사진)이 있으면
        if(!bookVo.getProduct_photo().isEmpty()){
            filename = bookVo.getProduct_photo().getOriginalFilename();
            String path = "C:\\seohaeProject\\upload\\";

            try {
                new File(path).mkdirs(); // 디렉토리 생성
                // 임시디렉토리(서버)에 저장된 파일을 지정된 디렉토리로 전송
                bookVo.getProduct_photo().transferTo(new File(path+filename));
            } catch (Exception e) {
                e.printStackTrace();
            }
            bookVo.setPdUrl(filename); // 파일 경로를 Vo에 저장
            bookService.insertBook(bookVo); // 상품을 등록
        }
		return "redirect:/bookList.do";
	}
	
	/**
	    * @Method BookUpdate
	    * @Date 2017. 11. 06.
	    * @Writter seohae
	    * @param bookVo
	    * @Discript 판매교재 수정페이지
	    * @Return String
	  */
	@RequestMapping(value="/bookUpdate", method=RequestMethod.POST)
	public String BookUpdate(@ModelAttribute("BookVo") BookVo bookVo, Model model, HttpSession session){
		BookVo list = bookService.bookDetail(bookVo);
		model.addAttribute("BookVo", list); 
		return "/book/bookUpdate";
	}
	
	/**
	    * @Method BookUpdateSave
	    * @Date 2017. 11. 06.
	    * @Writter seohae
	    * @param bookVo
	    * @Discript 판매교재 수정완료
	    * @Return String
	  */
	@RequestMapping(value="/bookUpdateSave", method=RequestMethod.POST)
	public String BookUpdateSave(@ModelAttribute("BookVo") BookVo bookVo, Model model, HttpSession session){
		String filename = "";
        // 첨부파일(상품사진)이 변경되면
        if(!bookVo.getProduct_photo().isEmpty()){
            filename = bookVo.getProduct_photo().getOriginalFilename();
            
            String path = "C:\\seohaeProject\\upload\\";
            try {
                new File(path).mkdirs(); // 디렉토리 생성
                // 임시디렉토리(서버)에 저장된 파일을 지정된 디렉토리로 전송
                bookVo.getProduct_photo().transferTo(new File(path+filename));
            } catch (Exception e) { //에러처리
                e.printStackTrace();
            }
            bookVo.setPdUrl(filename);
        // 첨부파일이 변경되지 않으면
        } else {
            // 기존의 파일명
        	BookVo vo2 = bookService.bookDetail(bookVo); 
            bookVo.setPdUrl(vo2.getPdUrl()); 
        }
        bookService.bookUpdateSave(bookVo);
        return "redirect:/bookList.do";
	}
	
	/**
	    * @Method BookDelete
	    * @Date 2017. 11. 06.
	    * @Writter seohae
	    * @param pdNo
	    * @Discript 판매교재 삭제
	    * @Return String
	  */
	@RequestMapping(value="/bookDelete", method=RequestMethod.POST)
	public String BookDelete(@RequestParam int pdNo){
		 // 상품 이미지 정보
        String filename = bookService.fileInfo(pdNo);
        
        String path = "C:\\seohaeProject\\upload\\";
        // 상품 이미지 삭제
        if(filename != null){
            File file = new File(path+filename);
            // 파일이 존재하면
            if (file.exists()){ 
                file.delete(); // 파일 삭제
            }
        }
        bookService.bookDelete(pdNo); 
        
        return "redirect:productList";
    }
	
}
