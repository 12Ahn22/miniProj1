package com.miniproj1.members;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	private String id;
	private String name;
	private String password;
	private String address;
	private String phone;
	private Gender gender;
	private Map<Integer, String> hobbies; // 모든 취미
	
	// 요청 처리를 위한 필드
	private String action;
	
	// 보여주기 위한 데이터
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
		if(this.gender.equals(Gender.F)) return "female";
		return "male";
	}
}
