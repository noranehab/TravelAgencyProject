package com.TravelAGency.TravelAgency.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {

 UserModel findFirstByEmail(String email);


}
