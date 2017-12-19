package com.scsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scsa.model.service.BankAPIService;
import com.scsa.model.service.UserService;
import com.scsa.model.vo.UserInfo;

/*boolean login(String userId, String password);
boolean logout();
boolean addUser(UserInfo user);
boolean updateUser(UserInfo user);
UserInfo getUserById(String userId);
UserInfo getUserByUserSeqNo(String userSeqNo);*/

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BankAPIService bankAPIService;


	// ������߰�
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String addUser(@RequestBody UserInfo user) {
		System.out.println("addUser ����");
		// auth ������ �ٸ� ���� ��������
		UserInfo userDetail = bankAPIService.getUserInfo(user);
		
		
		String message;
		if (userService.addUser(user)) message="��Ͽ� �����Ͽ����ϴ�";
		else message= "��Ͽ� �����Ͽ����ϴ�";
		
		return message;
	}

	//����ں���
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public void updateUser(@RequestBody UserInfo user) {
		userService.updateUser(user);
	}

	//���̵�� ���� ��������
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public UserInfo getUserById(Model model, @PathVariable String userId) {
		return userService.getUserById(userId);
	}
	
	@RequestMapping(value = "/userWithAccount/{userId}", method = RequestMethod.GET)
	public UserInfo getUserWithAccountListById(Model model, @PathVariable String userId) {
		
		return userService.getUserWithAccountListById(userId);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String login(@RequestBody UserInfo user) {
		System.out.println("��Ʈ�ѷ�");
		// ID, PW ��ġ Ȯ��
		UserInfo checkedUser = userService.login(user);
		
		String result;
		if (checkedUser == null) result = null;
		else {
			userService.updateToken(user);	// Token ����
			result = checkedUser.getUserId();
		}
		return result;
	}
	
	/*	@RequestMapping(value = "/user/{userSeqNo}", method = RequestMethod.GET)
	public String getUserByUserSeqNo(Model model, @PathVariable String userSeqNo) {
		System.out.println("������");
		model.addAttribute("user", userService.getUserById(userSeqNo));
		return "user_view";
	}*/

	/*
	 * @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
	 * public boolean deleteUserWithReturnValue(@PathVariable String userId) {
	 * boolean flag = userService.remove(userId);
	 * Logger.getLogger(this.getClass()).info("delete user : "+flag); return
	 * flag; }
	 */

	/*
	 * @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
	 * public void deleteUser(@PathVariable String userId) {
	 * userService.remove(userId); }
	 */

	/* @RequestMapping(value = "/xml/users", produces = "application/xml;charset=UTF-8") */

	/*
	 * @Controller public class HomeController {
	 * 
	 * private static final Logger logger =
	 * LoggerFactory.getLogger(HomeController.class);
	 * 
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * home(Locale locale, Model model) {
	 * logger.info("Welcome home! The client locale is {}.", locale);
	 * 
	 * Date date = new Date(); DateFormat dateFormat =
	 * DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	 * 
	 * String formattedDate = dateFormat.format(date);
	 * 
	 * model.addAttribute("serverTime", formattedDate );
	 * 
	 * return "home"; }
	 */
}
