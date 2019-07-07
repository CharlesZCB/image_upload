package com.cn.mapping;


import java.util.List;

import com.cn.bean.User;

public interface UserMapping {

	List<User> getAll();

	boolean save(User user);

}
