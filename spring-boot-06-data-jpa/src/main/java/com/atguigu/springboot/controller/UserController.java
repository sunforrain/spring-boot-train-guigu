package com.atguigu.springboot.controller;

import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser (@PathVariable Integer id) {
        // springboot2.x之后的版本findOne需要传入一个对象了,不再是id,返回值也不再是查询到的实体类
        User user = new User();
        user.setId(id);
        // Example要使用springboot的,不是hibernate的
        Example<User> example = Example.of(user);
        Optional<User> optional = userRepository.findOne(example);
        if(optional.isPresent()){
            user = optional.get();
            return user;
        }else{
            return null;
        }
    }

    @GetMapping("/user")
    public User insertUser (User user) {
        User userSave = userRepository.save(user);
        return userSave;
    }

}
