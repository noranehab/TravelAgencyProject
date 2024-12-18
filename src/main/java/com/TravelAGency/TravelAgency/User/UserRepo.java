package com.TravelAGency.TravelAgency.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {

 UserModel findFirstByEmail(String email);


    Optional<UserModel> findByEmail(String email);
}
