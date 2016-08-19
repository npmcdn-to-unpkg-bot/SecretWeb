package ca.tklab.core.springoveride.facebook;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.social.support.URIBuilder;

public class MptUtils {
	private static String appsecret_proof = null;
	static String calculateAppSecretProof(String token, String appSecret) {
		
			try {
				Mac mac = Mac.getInstance("HmacSHA256");
				SecretKeySpec secretKey = new SecretKeySpec(appSecret.getBytes("UTF-8"), "HmacSHA256");
				mac.init(secretKey);
				byte[] digest = mac.doFinal(token.getBytes());
				appsecret_proof =new String(Hex.encode(digest));
			} catch (Exception e) {
				e.printStackTrace();
				appsecret_proof= "";
			}
		
		return appsecret_proof;
	}
		static String getAppsecretProof() {
			return appsecret_proof;
		}
	
	static URIBuilder  addAssSecretProof(URIBuilder uriBuilder) {
		return uriBuilder.queryParam("appsecret_proof", appsecret_proof);
	}
}
