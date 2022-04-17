package tech.noetzold.remoteanalyser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.noetzold.remoteanalyser.model.UserImp;

@Repository
public interface UserRepository extends JpaRepository<UserImp, String>{

	UserImp findByUsername(String username);
}