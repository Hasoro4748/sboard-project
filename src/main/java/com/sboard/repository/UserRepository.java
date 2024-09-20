package com.sboard.repository;

import com.sboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public boolean existsByUid(String uid);
    public boolean existsByName(String name);
    public boolean existsByEmail(String email);
    public boolean existsByNick(String nick);
    public boolean existsByHp(String hp);
}
