package ca.tklab.secret.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import ca.tklab.secret.dto.ArticleForm;
import ca.tklab.secret.model.Article;
import ca.tklab.secret.repository.ArticleRepository;

public class ArticleServiceImpl implements ArticleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private ArticleRepository repository;
    
    
    @Autowired
    public ArticleServiceImpl(PasswordEncoder passwordEncoder, ArticleRepository repository) {
        this.repository = repository;
    }
    
    @Transactional
    @Override
    public Article registerArticle(ArticleForm articleData) {
    	LOGGER.debug("Registering new user account with information: {}", articleData);
    	
    	Article.Builder user = Article.getBuilder()
    			.content(articleData.getContent())
    			.imageUrl(articleData.getImageUrl());


    	Article registered = user.build();


        return repository.save(registered);
    }
}
