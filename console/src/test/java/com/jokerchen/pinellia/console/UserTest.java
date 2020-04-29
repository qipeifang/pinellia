package com.jokerchen.pinellia.console;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jokerchen.pinellia.common.model.Page;
import com.jokerchen.pinellia.common.util.DateUtil;
import com.jokerchen.pinellia.console.user.entity.User;
import com.jokerchen.pinellia.console.user.service.UserService;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-18 09:15:40  
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserService userService;

	//@Test
	public void pageUser() {
		Page data = userService.findUserPage("");
		System.out.println(data);
	}
	
	@Test
	public void updateUser() {
		try {
			User user = new User();
			user.setId(11);
			user.setPassword("11");
			user.setState(0);
			user.setUsername(DateUtil.dateToString(new Date()));
			System.out.println("done.............. ");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
