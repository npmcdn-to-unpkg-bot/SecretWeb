package ca.tklab.secret.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class ArticleForm {
	private String imageUrl;

    @NotEmpty
	private String content;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
