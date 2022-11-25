package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepo;

    public ArrayList findAllUsers() {

        try {
            final ArrayList lits = new ArrayList();
            for (UserModel user : userRepo.findAll()) {
                lits.add(user);
            }
            return lits;
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
    }

    public Boolean create(UserModel user){
        try {
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

    public Boolean delete(String id){
        try {
            if(userRepo.existsById(id)){
                userRepo.deleteById(id);
            return true;

            }
            else{
                LOGGER.warn("Invalid user id!");
                return false;
            }
             
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

}
