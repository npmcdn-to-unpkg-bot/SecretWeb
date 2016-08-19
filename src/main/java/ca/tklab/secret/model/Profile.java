package ca.tklab.secret.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ca.tklab.secret.model.base.BaseEntity;

@Entity
@Table(name = "cm_profile")
public class Profile extends BaseEntity<String> {
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
    private String id;


    @Column(name = "profile_img", nullable = true, unique = false)
	private String profileImg;

    @Column(name = "text", nullable = true, unique = false)
	private String text;    

    @Column(name = "profile_bg", nullable = true, unique = false)
	private String profileBG;   
    
    
    public String getProfileImg() {
		return profileImg;
	}


	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getProfileBG() {
		return profileBG;
	}


	public void setProfileBG(String profileBG) {
		this.profileBG = profileBG;
	}


	public String getId() {
        return id;
    }
    
	
	public String getDisplayName() {
		return account.getFirstName() + " " + account.getLastName();
	}
	
	 @OneToOne(optional=true)
     @JoinColumn(name = "ID") 
     private Account account;    
}
