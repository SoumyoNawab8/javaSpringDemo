package com.example.javaSpringDemo.repository;

// import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.mongodb.repository.Query;

import com.example.javaSpringDemo.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {
    
//     @Query("{name:'?0'}")
//     UserModel findItemByName(String name);
    
//     @Query(value="{dateOfJoining:'?0'}", fields="{'name' : 1}")
//    List<UserModel> findAll(String name);
    
//     public long count();

}