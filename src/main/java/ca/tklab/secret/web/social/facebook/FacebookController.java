/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.tklab.secret.web.social.facebook;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FeedOperations;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacebookController {

	private final Facebook facebook;

	@Inject
	public FacebookController(Facebook facebook) {
		this.facebook = facebook;
	}
	
	@Inject
	private ConnectionRepository connectionRepository;

	@RequestMapping(value="/facebook", method=RequestMethod.GET)
	public String home(Model model) {
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		if (connection == null) {
			return "redirect:/connect/facebook";
		}
		Facebook api = connection.getApi();
		
		model.addAttribute("profile", api.userOperations().getUserProfile());
		return "facebook/profile";
	}
	

	@RequestMapping(value="/facebook/feed", method=RequestMethod.GET)
	public String showFeed(Model model) {
		FeedOperations oper = facebook.feedOperations();
		PagedList<Post> feed = oper.getFeed();
		
		System.out.println("----> " + feed.size());
		
		model.addAttribute("feed", feed);
		return "facebook/feed";
	}
	
	@RequestMapping(value="/facebook/feed", method=RequestMethod.POST)
	public String postUpdate(String message) {
		facebook.feedOperations().updateStatus(message);
		return "redirect:/facebook/feed";
	}
	
	@RequestMapping(value="/facebook/friends", method=RequestMethod.GET)
	public String showFriends(Model model) {
		model.addAttribute("friends", facebook.friendOperations().getFriendProfiles());
		return "facebook/friends";
	}
	
	@RequestMapping(value="/facebook/albums", method=RequestMethod.GET)
	public String showAlbums(Model model) {
		model.addAttribute("albums", facebook.mediaOperations().getAlbums());
		return "facebook/albums";
	}
	
	@RequestMapping(value="/facebook/album/{albumId}", method=RequestMethod.GET)
	public String showAlbum(@PathVariable("albumId") String albumId, Model model) {
		model.addAttribute("album", facebook.mediaOperations().getAlbum(albumId));
		model.addAttribute("photos", facebook.mediaOperations().getPhotos(albumId));
		return "facebook/album";
	}
	
}
