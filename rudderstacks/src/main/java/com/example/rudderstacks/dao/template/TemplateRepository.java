package com.example.rudderstacks.dao.template;

import com.example.rudderstacks.entity.template.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    public Optional<Template> findBySourceType(@Param("source_type") String source_type);

    public List<Template> findAll();

    public boolean existsBySourceType(@Param("source_type") String source_type);
}
