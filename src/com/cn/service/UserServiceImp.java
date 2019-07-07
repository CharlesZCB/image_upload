package com.cn.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.bean.User;
import com.cn.mapping.UserMapping;


@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserMapping userMapping;

	@Override
	public boolean save(User user) {
		return userMapping.save(user);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userMapping.getAll();
	}

}
