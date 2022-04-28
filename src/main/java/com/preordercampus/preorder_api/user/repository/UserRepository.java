package com.preordercampus.preorder_api.user.repository;



import com.preordercampus.preorder_api.user.domain.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {


    public Optional<UserVO> findByEmail(String email);

    public boolean existsByEmail(String email);

    public Optional<UserVO> findByVerifyCode(String verifyCode);

}
