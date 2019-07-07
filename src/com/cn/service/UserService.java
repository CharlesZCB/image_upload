package com.cn.service;



import java.util.List;

import com.cn.bean.User;

public interface UserService {

	boolean save(User user);

	List<User> getAll();

}
