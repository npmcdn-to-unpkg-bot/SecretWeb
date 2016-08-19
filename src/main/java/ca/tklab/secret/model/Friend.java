package ca.tklab.secret.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "cm_friend")
@IdClass(FriendKey.class)
public class Friend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REQUESTER_ID", nullable = false)
	private String requesterID;

	@Id
	@Column(name = "ACCEPTANT_ID", nullable = false)
	private String acceptantID;

	@Id
	@Column(name = "ACCEPTED", nullable = false, columnDefinition = "boolean default false")
	private Boolean accepted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "requested_time", nullable = false)
	private Calendar requestedTime;

	@Column(name = "accepted_time", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Calendar acceptedTime;
}

// we can use @EmbeddedId too
class FriendKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REQUESTER_ID", nullable = false)
	private String requesterID;

	@Id
	@Column(name = "ACCEPTANT_ID", nullable = false)
	private String acceptantID;

}
