package com.example.rudderstacks.dao.template;

import com.example.rudderstacks.entity.template.Field;
import com.example.rudderstacks.entity.template.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    public Optional<Field> findByFieldName(@Param("field_name") String field_name);

    List<Field> findByTemplate(@Param("template") Template template);

    public boolean existsByFieldName(@Param("field_name") String field_name);
}
