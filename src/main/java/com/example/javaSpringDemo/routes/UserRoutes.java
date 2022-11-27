package com.example.javaSpringDemo.routes;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.example.javaSpringDemo.model.UserModel;
import com.example.javaSpringDemo.services.UserService;

@RestController
@RequestMapping("/api/users")

public class UserRoutes {

    @Autowired
    private UserService service;

    private static final Logger LOGGER = LogManager.getLogger(UserRoutes.class);

    public UserRoutes(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    @ResponseBody
    // Method
    public ArrayList getUsers() {
        try {
            return service.findAllUsers();
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }

    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserModel findUser(@PathVariable String id) {
        try {
            UserModel result = service.findUser(id);
            if(true){
                return result;

            }
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Document deleteUser(@PathVariable String id) {
        try {
            if(service.delete(id)==true){
                return Document.parse(new Document("message", "success").toJson());

            }
            else{
            return Document.parse(new Document("message", "failed").toJson());

            }
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
    }

    @PostMapping("/")
    @ResponseBody
    public Document createUser(UserModel userMdl){
        try {
            if(service.create(userMdl)==true){
                return Document.parse(new Document("message", "success").toJson());

            }
            else{
            return Document.parse(new Document("message", "failed").toJson());

            }
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public Document updateUser(@PathVariable String id,UserModel userMdl){
        LOGGER.info("My ID>>>>>>>>>>>"+id);
        try {
            if(service.updateUser(id,userMdl)==true){
                return Document.parse(new Document("message", "success").toJson());

            }
            else{
            return Document.parse(new Document("message", "failed").toJson());

            }
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
    }
}