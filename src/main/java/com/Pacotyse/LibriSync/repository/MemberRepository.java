package com.Pacotyse.LibriSync.repository;

import com.Pacotyse.LibriSync.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
