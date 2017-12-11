package com.scsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scsa.model.service.FriendsService;
import com.scsa.model.vo.UserInfo;

@RestController
public class FriendsController {

	private FriendsService friendsService;

	@Autowired
	public void setFriendsService(FriendsService friendsService) {
		this.friendsService = friendsService;
	}

	// ���̵�� ģ�� ��� ��ȸ
	@RequestMapping(value = "/friends_list_by_id.do")
	public String friends_list_by_Id(Model model, String userId) {
		List<UserInfo> list = friendsService.getFriendsById(userId);
		model.addAttribute("list", list);
		return "friends_list_by_id";
	}

	// �̸����� ģ�� ��� ��ȸ
	@RequestMapping(value = "/friends_list_by_name.do")
	public String friends_list_by_name(Model model, String username) {
		List<UserInfo> list = friendsService.getFriendsByName(username);
		model.addAttribute("list", list);
		return "friends_list_by_name";
	}

	// ģ�� ���
	@RequestMapping(value = "/add_friend.do")
	public String add_friend(Model model, @RequestParam(name = "userId") String userId,
			@RequestParam(name = "friendId") String friendId) {
		boolean result = friendsService.addFriend(userId, friendId);
		if (result) {
			model.addAttribute("msg", "ģ�� ��Ͽ� �����Ͽ����ϴ�");
		} else {
			model.addAttribute("msg", "ģ�� ��Ͽ� �����Ͽ����ϴ�");
		}
		model.addAttribute("userId", userId);
		return "friends_list_by_id";
	}

	// ģ�� ����
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
	public String delete_favorite_friend(Model model, @RequestParam(name = "userId") String userId,
			@RequestParam(name = "friendId") String friendId) {
		boolean result = friendsService.removeFavoriteFriend(userId, friendId);
		if (result) {
			model.addAttribute("msg", "ģ�� ��ã ���� �����Ͽ����ϴ�");
		} else {
			model.addAttribute("msg", "ģ�� ��ã ���� �����Ͽ����ϴ�");
		}
		model.addAttribute("userId", userId);
		return "favorite_friends_list_by_Id";
	}
}
