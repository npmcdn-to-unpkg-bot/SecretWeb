package ca.tklab.secret.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.tklab.secret.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Profile findById(String id);
}
