package com.preordercampus.preorder_api.user.repository;


import com.limbae.preorder.anabada_api.user.domain.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {



}
