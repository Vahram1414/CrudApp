package org.example.repository;

import java.util.List;
import org.example.dto.Skill;

public interface SkillRepository {

  List<Skill> findAll();

  void save(Skill skill);

  Skill findById(Long id);

  void deleteById(Long id);
}
