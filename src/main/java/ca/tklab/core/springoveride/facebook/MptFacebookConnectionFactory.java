package ca.tklab.core.springoveride.facebook;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookAdapter;

public class MptFacebookConnectionFactory extends OAuth2ConnectionFactory<Facebook> {

	public MptFacebookConnectionFactory(String appId, String appSecret) {
		super("facebook", new MptFacebookServiceProvider(appId, appSecret, null), new FacebookAdapter());
	}

}
