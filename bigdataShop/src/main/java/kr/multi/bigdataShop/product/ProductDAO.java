package kr.multi.bigdataShop.product;

import java.util.List;

import kr.multi.bigdataShop.product.comment.ProductCommentDTO;

public interface ProductDAO {
	List<ProductDTO> productlist (String category);
	List<ProductDTO> hitproduct();
	List<ProductDTO> newproduct();
	ProductDTO read(String prd_no);
	List<ProductCommentDTO> commentlist(String prd_no);
}