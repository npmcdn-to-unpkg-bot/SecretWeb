package ca.tklab.core.springoveride.facebook;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookServiceProvider;

public class MptFacebookServiceProvider extends FacebookServiceProvider {
	private final String appNamespace;
	private final String appSecret;
	private final String appId;

	/**
	 * Creates a FacebookServiceProvider for the given application ID, secret,
	 * and namespace.
	 *
	 * @param appId
	 *            The application's App ID as assigned by Facebook
	 * @param appSecret
	 *            The application's App Secret as assigned by Facebook
	 * @param appNamespace
	 *            The application's App Namespace as configured with Facebook.
	 *            Enables use of Open Graph operations.
	 */
	public MptFacebookServiceProvider(String appId, String appSecret, String appNamespace) {
		super(appId, appSecret, appNamespace);
		this.appNamespace = appNamespace;
		this.appSecret = appSecret;
		this.appId = appId;
	}

	@Override
	public Facebook getApi(String accessToken) {
		return new MptFacebookTemplate(accessToken, appId, appSecret, appNamespace);
	}
}