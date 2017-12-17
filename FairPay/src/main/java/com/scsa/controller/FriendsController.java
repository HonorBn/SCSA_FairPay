package com.scsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scsa.data.Ours;
import com.scsa.model.service.FriendsService;
import com.scsa.model.vo.FriendsInfo;
import com.scsa.model.vo.FriendsList;
import com.scsa.model.vo.MeetingInfo;
import com.scsa.model.vo.MeetingList;
import com.scsa.model.vo.UserInfo;

@RestController
public class FriendsController {

	private FriendsService friendsService;

	@Autowired
	public void setFriendsService(FriendsService friendsService) {
		this.friendsService = friendsService;
	}
	
	// 아이디로 친구 목록 조회  : done
	@RequestMapping(value = "/friends/list/{userId}", method = RequestMethod.GET)
	public FriendsList getFriendsByUserId(@PathVariable String userId) {
		System.out.println("아이디로 친구목록 조회 요청 발생 userId : " + userId);
		FriendsList friendsList = new FriendsList();
		friendsList.setFriendsList(friendsService.getFriendsById(userId));
		return friendsList;
	}

	// 이름으로 친구 목록 조회  : 보류
	@RequestMapping(value = "/friends/list/name/{username}")
	public FriendsList getFriendsByUsername(@PathVariable String username) {
		System.out.println("아이디로 친구목록 조회 요청 발생 userId : " + username);
		FriendsList friendsList = new FriendsList();
		friendsList.setFriendsList(friendsService.getFriendsByName(username));
		return friendsList;
	}

	// 친구 등록  : done
	@RequestMapping(value = "/friends/add", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")
	public String addFriend(@RequestBody FriendsInfo friend) {
		System.out.println("친구 생성 요청 발생 : "+friend);
		boolean result = friendsService.addFriend(friend);
		if (result) {
			return "친구가 등록되었습니다.";
		} else {
			return "친구등록에 실패하였습니다.";
		}
	}

	// 친구 삭제  : 보류
	@RequestMapping(value = "/delete_friend", method = RequestMethod.DELETE)
	public String delete_friend(@RequestBody Ours ours) {
		boolean result = friendsService.removeFriend(ours.getMyId(), ours.getYourId());
		
		String msg = null;
		if (result) msg = "친구 삭제 성공하였습니다";
		else msg = "친구 삭제 실패하였습니다";
		
		return msg;
	}

	// 아이디로 즐겨찾는 친구 목록 조회
	@RequestMapping(value = "/favorite_friends_list_by_Id.do/{userId}")
	public String favorite_friends_list_by_Id(Model model,@PathVariable String userId) {
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
