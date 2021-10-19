package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {

	Member loginMember(Member m) throws Exception;

	void insertMember(Member m);

}
