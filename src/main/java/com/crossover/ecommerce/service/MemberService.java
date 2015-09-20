package com.crossover.ecommerce.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.ecommerce.domain.Member;
import com.crossover.ecommerce.repository.MemberDao;

@Service
@Transactional
public class MemberService {

	@Resource
	private MemberDao memberDao;

	public List<Member> getAll() {
		return memberDao.findAll();
	}

}
