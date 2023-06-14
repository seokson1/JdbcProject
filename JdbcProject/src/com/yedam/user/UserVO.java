package com.yedam.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter setter toString 등 만들어짐.
@AllArgsConstructor // 전체 필드 생성자
@NoArgsConstructor // 기본 생성자 형성
public class UserVO {
	
	private String userId;
	private String userPw;
	private String userName;
	private String userBirth;
	private String userPhone;
	private String userAddr;
	
}
