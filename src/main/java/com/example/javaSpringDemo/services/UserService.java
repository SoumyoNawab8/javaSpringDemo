package com.example.javaSpringDemo.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

import javax.swing.text.Document;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.example.javaSpringDemo.model.UserModel;
import com.example.javaSpringDemo.repository.UserRepository;

import springfox.documentation.spring.web.json.Json;

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
            user.setDateOfJoining();
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

    public Boolean delete(String id) {
        try {
            if (userRepo.existsById(id)) {
                userRepo.deleteById(id);
                return true;
            } else {
                LOGGER.warn("Invalid user id!");
                return false;
            }

        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

    public UserModel findUser(String id){
        try {
            if (userRepo.existsById(id)) {
                UserModel user= userRepo.findById(id).get();
                return user;
            } else {
                LOGGER.warn("Invalid user id!");
                throw new IllegalStateException("Invalid user id!");
            }

        } catch (Exception e) {
            LOGGER.warn("Invalid user id!"+e);
        }
        return null;
    }

    public Boolean updateUser(String id, UserModel payload){
        try {
            if (userRepo.existsById(id)) {
                Optional<UserModel> activeUser = userRepo.findById(id);
                UserModel _user=activeUser.get();
                _user.setName(payload.getName());
                _user.setLastUpdateTime();
                userRepo.save(_user);
            return true;
            }
            else {
                LOGGER.warn("Invalid user id!");
                throw new IllegalStateException("Invalid user id!");
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

}
