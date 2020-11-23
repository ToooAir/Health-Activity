package com.prlab.activityWeb.model.repository;

import com.prlab.activityWeb.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
