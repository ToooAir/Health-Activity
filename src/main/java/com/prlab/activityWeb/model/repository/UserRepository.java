package com.prlab.activityWeb.model.repository;

import com.prlab.activityWeb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
