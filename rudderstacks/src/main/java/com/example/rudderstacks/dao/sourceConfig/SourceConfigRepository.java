package com.example.rudderstacks.dao.sourceConfig;

import com.example.rudderstacks.entity.sourceConfig.SourceConfig;
import com.example.rudderstacks.entity.template.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceConfigRepository extends JpaRepository<SourceConfig, Long> {
    List<SourceConfig> findByTemplate(@Param("template") Template template);
}
