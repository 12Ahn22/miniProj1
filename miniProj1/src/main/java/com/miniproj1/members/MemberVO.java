package com.miniproj1.members;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberVO {
	private String id;
	private String name;
	private String password;
	private String address;
	private String phone;
	private Gender gender;
	
	public MemberVO(String id, String name, String address, String phone, String gender) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		if(gender.equalsIgnoreCase("f")) this.gender = Gender.F;	
		else this.gender = Gender.M;
	}
	
	public static enum Gender {
        F,
        M
    }
}
