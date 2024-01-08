package com.vishnuparasu.EnforcementDirectorate.repository;

import com.vishnuparasu.EnforcementDirectorate.entity.EdUserEntity;
import com.vishnuparasu.EnforcementDirectorate.entity.EdUserPaymentEntity;
import org.aspectj.weaver.ast.Literal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EnableJpaRepositories
@Repository
public interface EdUserPaymentRepo extends JpaRepository<EdUserPaymentEntity,String> {
    @Query("SELECT u from EdUserPaymentEntity u where u.eduidSender = :eduidSender")
    Optional<EdUserPaymentEntity> findSenderByEduid(@Param("eduidSender")String eduidSender);

    @Query("SELECT u from EdUserPaymentEntity u where u.eduidRecevier = :eduidRecevier")
    Optional<EdUserPaymentEntity> findRecevierByEduid(@Param("eduidRecevier")String eduidRecevier);


    @Query("SELECT u from EdUserPaymentEntity u where u.isLegal = :isLegal")
    List<EdUserPaymentEntity> findAllByCompliantBoolean(@Param("isLegal")String isLegal);
}
