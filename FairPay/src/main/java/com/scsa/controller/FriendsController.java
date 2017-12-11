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

	// 아이디로 친구 목록 조회
	@RequestMapping(value = "/friends_list_by_id.do")
	public String friends_list_by_Id(Model model, String userId) {
		List<UserInfo> list = friendsService.getFriendsById(userId);
		model.addAttribute("list", list);
		return "friends_list_by_id";
	}

	// 이름으로 친구 목록 조회
	@RequestMapping(value = "/friends_list_by_name.do")
	public String friends_list_by_name(Model model, String username) {
		List<UserInfo> list = friendsService.getFriendsByName(username);
		model.addAttribute("list", list);
		return "friends_list_by_name";
	}

	// 친구 등록
	@RequestMapping(value = "/add_friend.do")
	public String add_friend(Model model, @RequestParam(name = "userId") String userId,
			@RequestParam(name = "friendId") String friendId) {
		boolean result = friendsService.addFriend(userId, friendId);
		if (result) {
			model.addAttribute("msg", "친구 등록에 성공하였습니다");
		} else {
			model.addAttribute("msg", "친구 등록에 실패하였습니다");
		}
		model.addAttribute("userId", userId);
		return "friends_list_by_id";
	}

	// 친구 삭제
	@RequestMapping(value = "/delete_friend.do")
	public String delete_friend(Model model, @RequestParam(name = "userId") String userId,
			@RequestParam(name = "friendId") String friendId) {
		boolean result = friendsService.removeFriend(userId, friendId);
		if (result) {
			model.addAttribute("msg", "친구 삭제 성공하였습니다");
		} else {
			model.addAttribute("msg", "친구 삭제 실패하였습니다");
		}
		model.addAttribute("userId", userId);
		return "friends_list_by_id";
	}

	// 아이디로 즐겨찾는 친구 목록 조회
	@RequestMapping(value = "/favorite_friends_list_by_Id.do")
	public String favorite_friends_list_by_Id(Model model, String userId) {
		List<UserInfo> list = friendsService.getFavoriteFriendsById(userId);
		model.addAttribute("list", list);
		return "favorite_friends_list_by_Id";
	}

	// 즐겨찾는 친구 등록
	@RequestMapping(value = "/add_favorite_friend.do")
	public String add_favorite_friend(Model model, @RequestParam(name = "userId") String userId,
			@RequestParam(name = "friendId") String friendId) {
		boolean result = friendsService.addFavoriteFriend(userId, friendId);
		if (result) {
			model.addAttribute("msg", "친구 즐찾 등록 성공하였습니다");
		} else {
			model.addAttribute("msg", "친구 즐찾 등록 실패하였습니다");
		}
		model.addAttribute("userId", userId);
		return "favorite_friends_list_by_Id";
	}

	// 즐겨찾는 친구 해제
	@RequestMapping(value = "/delete_favorite_friend.do")
	public String delete_favorite_friend(Model model, @RequestParam(name = "userId") String userId,
			@RequestParam(name = "friendId") String friendId) {
		boolean result = friendsService.removeFavoriteFriend(userId, friendId);
		if (result) {
			model.addAttribute("msg", "친구 즐찾 해제 성공하였습니다");
		} else {
			model.addAttribute("msg", "친구 즐찾 해제 실패하였습니다");
		}
		model.addAttribute("userId", userId);
		return "favorite_friends_list_by_Id";
	}
}
