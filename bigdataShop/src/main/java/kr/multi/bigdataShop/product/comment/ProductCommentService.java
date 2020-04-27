package kr.multi.bigdataShop.product.comment;

import java.util.List;

public interface ProductCommentService {
	int insert(ProductCommentDTO comment);
	List<CommentDTO> commentList();
}
