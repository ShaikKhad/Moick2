package com.jsp.curdoperation222;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class UserController {
	
@Autowired
UserRepository ur;
@GetMapping("/users")
public Page<User> getAllUsers(@RequestParam int page,@RequestParam int size,@RequestParam String[]sort){
    Pageable pageable=PageRequest.of(page, size,getSort(sort));
    
	return ur.getAllUsers(pageable);
}
private Sort getSort(String[] sort) {
	return sort.length;
}
@PostMapping("/users")
public User saveUser(@RequestBody User user) {
	return Userservice.saveUser();
}

}
