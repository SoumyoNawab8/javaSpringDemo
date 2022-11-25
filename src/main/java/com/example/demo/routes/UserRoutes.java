package com.example.demo.routes;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.example.demo.model.UserModel;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/users")

public class UserRoutes {

    @Autowired
    private UserService service;

    private static final Logger LOGGER = LogManager.getLogger(UserRoutes.class);

    public UserRoutes(UserService service) {
        this.service = service;
    }

    @RequestMapping("/")
    @ResponseBody
    // Method
    public ArrayList getUsers() {
        try {
            LOGGER.info(
                    "Userssssssssssssss>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            return service.findAllUsers();
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }

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
            // TODO: handle exception
        }
    }
}