package com.boydlever.auth.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boydlever.auth.models.User;

@Repository
public interface UserRepository extends CrudRepository <User,Long>{
	Optional<User> findByEmail(String email);
	//You can also make use of findById
	//CrudRepository already has findById in it
	//For every table connected to MySQL
}
