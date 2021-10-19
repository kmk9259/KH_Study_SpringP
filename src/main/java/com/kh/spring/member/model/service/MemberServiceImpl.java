package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.common.exception.CommException;
import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public Member loginMember(Member m) throws Exception{
		Member loginUser = memberDao.loginMember(sqlSession, m);
		if(loginUser == null) {
			throw new Exception("loginUser null");
		}
		return loginUser;
	}

	@Override
	public void insertMember(Member m) {
		int result = memberDao.insertMember(sqlSession, m);
		if(result < 0) {
			throw new CommException("회원 가입 실패");
		}
		
	}

}
