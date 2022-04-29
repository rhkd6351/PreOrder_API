package com.preordercampus.preorder_api.user.repository;



import com.preordercampus.preorder_api.user.domain.SchoolVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolVO, Long> {


}
