package ca.tklab.secret.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.tklab.secret.model.Friend;

public interface FriendRepository  extends JpaRepository<Friend, Long> {
		@Query("SELECT id FROM Account "
		+ " WHERE id = :uid "
		+ " UNION ALL "
		+ " SELECT acceptantID "
		+ "  FROM Friend "
		+ " WHERE requester_id = :uid  and accepted = true ")		

	public List<String> getFriendsIds(@Param("uid") String id);
}
