package kr.multi.bigdataShop.product.comment;

public class CommentDTO {
	String word;
	String hit;
	
	public CommentDTO() {
		
	}

	public CommentDTO(String word, String hit) {
		super();
		this.word = word;
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "CommentDTO [word=" + word + ", hit=" + hit + "]";
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}
	
}
