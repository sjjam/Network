package kr.multi.bigdataShop.product.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCommentServiceImpl implements ProductCommentService{
	@Autowired
	ProductCommentDAO dao;
	@Override
	public int insert(ProductCommentDTO comment) {
		return dao.insert(comment);
	}
	@Override
	public List<CommentDTO> commentList() {
		return dao.commentList();
	}
	
}
