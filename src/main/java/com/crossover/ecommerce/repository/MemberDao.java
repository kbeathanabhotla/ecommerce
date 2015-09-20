package com.crossover.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.crossover.ecommerce.domain.Member;

public interface MemberDao extends Repository<Member, Integer> {

	List<Member> findAll();

	List<Member> findByUsername(String username);

}
