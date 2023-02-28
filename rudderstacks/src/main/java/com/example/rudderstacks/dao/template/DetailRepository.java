package com.example.rudderstacks.dao.template;

import com.example.rudderstacks.entity.template.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    public Optional<Detail> findById(@Param("id") String id);

    public boolean existsById(@Param("id") String id);
}
