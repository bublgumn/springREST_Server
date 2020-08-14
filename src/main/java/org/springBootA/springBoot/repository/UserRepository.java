package org.springBootA.springBoot.repository;

import org.springBootA.springBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Query("select u from User u JOIN FETCH u.role where u.email = ?1")
    User findByEmail(String email);

    @Transactional
    @Override
    @Query("select distinct u FROM User u JOIN FETCH u.role")
    List<User> findAll();

    @Transactional
    @Modifying
    @Query("delete from User u where u.id = ?1")
    void deleteById(Long aLong);

}
