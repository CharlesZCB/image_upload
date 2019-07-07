package com.cn.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cn.bean.User;
import com.cn.service.UserService;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
@RequestMapping(value="/upload",method=RequestMethod.POST)
public Object uploadFile(@RequestParam( value="file",required=false) CommonsMultipartFile file,HttpServletRequest request,String username) throws IOException {
	
	System.err.println("------------------------------");
	 request.setCharacterEncoding("utf-8");
	 if (!file.isEmpty()) {
		BASE64Encoder encoder = new BASE64Encoder();
		String image = encoder.encode(file.getBytes());
		User user = new User();
		user.setImage(image);
		user.setUsername(username);
		userService.save(user);
	}
	 return "redirect:/user/showall";
	
  }

 @RequestMapping("/showall")
 public String show(HttpServletRequest request,HttpServletResponse response) throws IOException {
	 List<User> list = userService.getAll();
	 List<Map<String, Object>> listmap = new ArrayList<Map<String,Object>>();
	 for (User user :list) {
		byte[] bytes=(byte[])user.getImage();
		String data = new String(bytes, "UTF-8");
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] bs = base64Decoder.decodeBuffer(data);
		for (int i = 0; i < bs.length; i++) {
			if (bs[i]<0) {
				bs[i] += 256;
			}
		}
		response.setContentType("image/jpeg");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(bs);
		outputStream.flush();
		outputStream.close();
		Map<String, Object> map = new HashMap<String, Object>();
			/*
			 * map.put("imgURL", "00"); map.put("username", user.getUsername());
			 * 
			 * listmap.add(map);
			 */
	}
	 
	 
	// request.setAttribute("list", listmap);
	 return "/user/show";
 }

}
