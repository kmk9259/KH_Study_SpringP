package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Override
	public Member loginMember(BCryptPasswordEncoder bCryptPasswordEncoder, Member m) {
		Member loginUser = memberDao.loginMember(sqlSession, m);
		if(loginUser == null) {
			throw new CommException("loginUser null");
		}
		//matches(평문, 암호화)->true, false 반환
		if(!bCryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
			//복호화랑 일치하지 않으면
			throw new CommException("암호불일치");
		}
		return loginUser;
	}

	@Override
	public Member updateMember(Member m) {
		int result = memberDao.updateMember(sqlSession,m);
		if(result>0) {
			Member loginUser = memberDao.loginMember(sqlSession, m);
			return loginUser;
		}else {
			throw new CommException("회원 수정 실패");
		}		
	}

	@Override
	public void deleteMember(String userId) {
		int result = memberDao.deleteMember(sqlSession, userId);
		if(result < 0) {
			throw new CommException("회원 삭제 실패");
		}	
		
	}

	
	

}
