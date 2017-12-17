package com.scsa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scsa.data.Pay;
import com.scsa.model.service.FriendsService;
import com.scsa.model.vo.FriendsInfo;
import com.scsa.model.vo.FriendsList;
import com.scsa.model.vo.UserInfo;

@RestController
public class FriendsController {

	private FriendsService friendsService;

	@Autowired
	public void setFriendsService(FriendsService friendsService) {
		this.friendsService = friendsService;
	}
	
	// ���̵�� ģ�� ��� ��ȸ  : done
	@RequestMapping(value = "/friends/list/{userId}", method = RequestMethod.GET)
	public FriendsList getFriendsByUserId(@PathVariable String userId) {
		System.out.println("���̵�� ģ����� ��ȸ ��û �߻� userId : " + userId);
		FriendsList friendsList = new FriendsList();
		friendsList.setFriendsList(friendsService.getFriendsById(userId));
		return friendsList;
	}

	// �̸����� ģ�� ��� ��ȸ  : ����
	@RequestMapping(value = "/friends/list/name/{username}")
	public FriendsList getFriendsByUsername(@PathVariable String username) {
		System.out.println("���̵�� ģ����� ��ȸ ��û �߻� userId : " + username);
		FriendsList friendsList = new FriendsList();
		friendsList.setFriendsList(friendsService.getFriendsByName(username));
		return friendsList;
	}

	// ģ�� ���  : done
	@RequestMapping(value = "/friends/add", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addFriend(@RequestBody FriendsInfo friend) {
		System.out.println("ģ�� ���� ��û �߻� : "+friend);
		boolean result = friendsService.addFriend(friend);
		if (result) {
			return "ģ���� ��ϵǾ����ϴ�.";
		} else {
			return "ģ����Ͽ� �����Ͽ����ϴ�.";
		}
	}

	// ģ�� ����  : ����
	@RequestMapping(value = "/delete_friend.do")
	public String delete_friend(Model model, @RequestParam(name = "userId") String userId,
			@RequestParam(name = "friendId") String friendId) {
		boolean result = friendsService.removeFriend(userId, friendId);
		if (result) {
			model.addAttribute("msg", "ģ�� ���� �����Ͽ����ϴ�");
		} else {
			model.addAttribute("msg", "ģ�� ���� �����Ͽ����ϴ�");
		}
		model.addAttribute("userId", userId);
		return "friends_list_by_id";
	}

	// ���̵�� ���ã�� ģ�� ��� ��ȸ
	@RequestMapping(value = "/favorite_friends_list_by_Id.do")
	public String favorite_friends_list_by_Id(Model model, String userId) {
		List<UserInfo> list = friendsService.getFavoriteFriendsById(userId);
		model.addAttribute("list", list);
		return "favorite_friends_list_by_Id";
	}

	// ���ã�� ģ�� ���
	@RequestMapping(value = "/add_favorite_friend.do")
	public String add_favorite_friend(Model model, @RequestParam(name = "userId") String userId,
			@RequestParam(name = "friendId") String friendId) {
		boolean result = friendsService.addFavoriteFriend(userId, friendId);
		if (result) {
			model.addAttribute("msg", "ģ�� ��ã ��� �����Ͽ����ϴ�");
		} else {
			model.addAttribute("msg", "ģ�� ��ã ��� �����Ͽ����ϴ�");
		}
		model.addAttribute("userId", userId);
		return "favorite_friends_list_by_Id";
	}

	// ���ã�� ģ�� ����
	@RequestMapping(value = "/delete_favorite_friend.do")
	public String delete_favorite_friend(@RequestBody Map<String, String> ourId) {
		boolean result = friendsService.removeFavoriteFriend(ourId.get("myId"), ourId.get("yourId"));
		String msg;
		if (result) msg = "ģ�� ��ã ���� �����Ͽ����ϴ�";
		else msg = "ģ�� ��ã ���� �����Ͽ����ϴ�";
		return msg;
	}
}
