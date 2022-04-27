package com.preordercampus.preorder_api.user.repository;



import com.preordercampus.preorder_api.user.domain.AuthVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthVO, String> {


}
