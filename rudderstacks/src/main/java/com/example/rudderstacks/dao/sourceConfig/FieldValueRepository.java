package com.example.rudderstacks.dao.sourceConfig;

import com.example.rudderstacks.entity.sourceConfig.FieldValue;
import com.example.rudderstacks.entity.sourceConfig.SourceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldValueRepository extends JpaRepository<FieldValue, Long> {
    List<FieldValue> findBySourceConfig(@Param("sourceConfig") SourceConfig sourceConfig);
}
