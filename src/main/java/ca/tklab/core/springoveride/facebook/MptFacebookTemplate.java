package ca.tklab.core.springoveride.facebook;

import java.net.URI;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.api.impl.json.FacebookModule;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MptFacebookTemplate extends FacebookTemplate {
	private String token;
	private String appSecret;
	private FeedOperations feedOperations;
	private ObjectMapper objectMapper;

	public MptFacebookTemplate(String accessToken) {
		super(accessToken);
		this.token = accessToken;
	}

	public MptFacebookTemplate(String accessToken, String applicationNamespace) {
		super(accessToken, applicationNamespace);
		this.token = accessToken;
	}

	public MptFacebookTemplate(String accessToken, String applicationNamespace, String appId) {
		super(accessToken, applicationNamespace, appId);
		this.token = accessToken;
	}

	public MptFacebookTemplate(String accessToken, String appId, String appSecret, String applicationNamespace) {
		super(accessToken, applicationNamespace, appId);
		this.appSecret = appSecret;
		this.token = accessToken;

		this.feedOperations = new MptFeedTemplate(this, getRestTemplate(), objectMapper);
	}
	
	@Override
	protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
		objectMapper = new ObjectMapper();				
		objectMapper.registerModule(new FacebookModule());
		converter.setObjectMapper(objectMapper);		
		return converter;
	}
	
	@Override
	public FeedOperations feedOperations() {
		return feedOperations;
	}

	@Override
	public <T> T fetchObject(String objectId, Class<T> type, MultiValueMap<String, String> queryParameters) {
		queryParameters.add("appsecret_proof", MptUtils.calculateAppSecretProof(token, appSecret));
		URI uri = URIBuilder.fromUri(getBaseGraphApiUrl() + objectId).queryParams(queryParameters).build();
		return getRestTemplate().getForObject(uri, type);
	}
	
	 


}