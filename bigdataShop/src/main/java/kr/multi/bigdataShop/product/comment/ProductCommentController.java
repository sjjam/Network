package kr.multi.bigdataShop.product.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

	@Controller
	public class ProductCommentController {
	@Autowired
	ProductCommentService service;
	
	@RequestMapping(value="/comment/write.do", method=RequestMethod.POST)
	public String insert(ProductCommentDTO comment) {
	System.out.println("**************"+comment);
	int result = service.insert(comment);
	System.out.println("########################"+result);
	return "redirect:/product/read.do?prd_no="+comment.prd_no;
	}
	
	@RequestMapping(value="/comment/result.do", method=RequestMethod.GET)
	public ModelAndView commentView() {
		ModelAndView mav = new ModelAndView();
		List<CommentDTO> commentlist = service.commentList();
		mav.addObject("commentlist", commentlist);
		mav.setViewName("comment/result");
		return mav;
	}
}
