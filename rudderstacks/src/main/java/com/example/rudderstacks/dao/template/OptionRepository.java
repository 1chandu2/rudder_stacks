package com.example.rudderstacks.dao.template;

import com.example.rudderstacks.entity.template.Detail;
import com.example.rudderstacks.entity.template.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    public Optional<Option> findById(@Param("id") String id);
    List<Option> findByDetail(@Param("detail") Detail detail);

    public boolean existsById(@Param("id") String id);
}
