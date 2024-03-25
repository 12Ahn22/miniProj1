package com.miniproj1.members;

import lombok.Getter;

@Getter
public class MemberVO {
	private String id;
	private String name;
	private String password;
	private String address;
	private String phone;
	private Gender gender;
	
	public enum Gender {
        MALE,
        FEMALE
    }
}
