package com.dojo.eventos.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dojo.eventos.models.User;

public interface UserRepo extends CrudRepository<User, Long>{
	
	User findByEmail(String email);

}
