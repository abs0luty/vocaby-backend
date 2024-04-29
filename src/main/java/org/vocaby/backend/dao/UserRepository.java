package org.vocaby.backend.dao;

import org.springframework.data.repository.CrudRepository;
import org.vocaby.backend.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
