package ca.tklab.secret.service;

import ca.tklab.secret.dto.ArticleForm;
import ca.tklab.secret.model.Article;

public interface ArticleService {

	public Article registerArticle(ArticleForm articleData);
}
