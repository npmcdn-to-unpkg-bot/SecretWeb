package ca.tklab.secret.web.secret;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import ca.tklab.secret.dto.ArticleForm;
import ca.tklab.secret.model.Article;
import ca.tklab.secret.model.Profile;
import ca.tklab.secret.model.base.ApiResult;
import ca.tklab.secret.repository.ArticleRepository;
import ca.tklab.secret.repository.ProfileRepository;

@RestController
@SessionAttributes("user")
public class SecretController {
	private static final Logger logger = LoggerFactory.getLogger(SecretController.class);

    private final ArticleRepository articleRepository;
	private final ProfileRepository profileRepository;
   
    @Inject
    public SecretController(
    		ArticleRepository articleRepository,
    		ProfileRepository profileRepository) {
        this.articleRepository = articleRepository;
        this.profileRepository = profileRepository;
    }
	
	@RequestMapping(value="/api/profile/owner", method=RequestMethod.GET)
	public ApiResult getProfile(Model model, Principal currentUser) {
		ApiResult result = new ApiResult();
		logger.debug("CurrentUser: {}", currentUser.getName());

		
		Profile profile =  profileRepository.findById(currentUser.getName());
		result.setData(profile);
		return result;
	}
	
	@RequestMapping(value="/api/article", method=RequestMethod.GET)
	public ApiResult getArticle(Model model, Principal currentUser) {
		ApiResult result = new ApiResult();
		logger.debug("CurrentUser: {}", currentUser.getName());

		
		List<Article> articles =  articleRepository.getArticlesByUID(currentUser.getName());
		result.setData(articles);
		return result;
	}
	
	@RequestMapping(value="/api/article", method=RequestMethod.POST)
	public ApiResult registerArticle(Model model, 
			Principal currentUser, 
			@RequestBody @Valid ArticleForm form,  
			BindingResult formBinding) {
		ApiResult result = new ApiResult();

		if(formBinding.hasErrors() ) {
			result.setErrorMsg("Error!!!");
		} else {
			Profile writer = profileRepository.findById(currentUser.getName());
			Article article =  Article.getBuilder()
					.profile(writer)
					.content(form.getContent())
					.build();
				articleRepository.save(article);
	
		}
		return result;
	}
}
