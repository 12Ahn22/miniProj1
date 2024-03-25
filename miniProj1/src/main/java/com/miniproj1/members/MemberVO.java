package com.miniproj1.members;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MemberVO {
	private String id;
	private String name;
	private String password;
	private String address;
	private String phone;
	private Gender gender;
	@Setter
	private List<String> hobbies;
	
	public MemberVO(String id, String name, String address, String phone, String gender, String[] hobbies) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		if(gender.equalsIgnoreCase("f")) this.gender = Gender.F;	
		else this.gender = Gender.M;
	}
	
	public MemberVO(String id, String name, String address, String phone, String gender) {
		this(id, name, address, phone, gender, null);
	}
	
	public static enum Gender {
        F,
        M
    }
	
	public String getGender() {
		if(this.gender.equals(Gender.F)) return "여";
		return "남";
	}
}
