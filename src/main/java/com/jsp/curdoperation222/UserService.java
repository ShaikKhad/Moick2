package com.jsp.curdoperation222;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
@Autowired
private UserRepository ur;
public Page<User> getAllUser(Pageable pageable){
	return ur.findAll(pageable);
}
}
