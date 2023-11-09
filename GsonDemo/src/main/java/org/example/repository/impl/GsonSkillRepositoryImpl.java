package org.example.repository.impl;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static org.example.enums.Status.DELETED;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import lombok.SneakyThrows;
import org.example.dto.Skill;
import org.example.repository.SkillRepository;

public class GsonSkillRepositoryImpl implements SkillRepository {

  private static final Gson GSON = new Gson();
  private static final String PATH = "/skills.json";

  @SneakyThrows
  @Override
  public List<Skill> findAll() {
    return GSON.fromJson(new InputStreamReader(requireNonNull(getClass().getResourceAsStream(PATH))),
        new TypeToken<List<Skill>>() {
        }.getType());
  }

  @SneakyThrows
  @Override
  public void save(Skill skill) {
    var skills = findAll();
    if (isNull(skill.getId())) {
      skill.setId(getNextId(skills));
      skills.add(skill);
    } else {
      var updatedSkill = findById(skills, skill.getId());
      updatedSkill.setName(skill.getName());
      updatedSkill.setStatus(skill.getStatus());
    }
    save(skills);
  }

  @Override
  public Skill findById(Long id) {
    return findById(findAll(), id);
  }

  private Skill findById(List<Skill> skills, Long id) {
    return skills.stream().filter(skill -> Objects.equals(skill.getId(), id)).findFirst().orElseThrow(RuntimeException::new);
  }

  @Override
  public void deleteById(Long id) {
    var skills = findAll();
    var skill = findById(skills, id);
    skill.setStatus(DELETED);
    save(skills);
  }

  private Long getNextId(List<Skill> skills) {
    return skills.stream().mapToLong(Skill::getId).max().orElse(0) + 1;
  }

  @SneakyThrows
  private void save(List<Skill> skills) {
    Files.writeString(new File(getClass().getResource(PATH).getPath()).toPath(), GSON.toJson(skills));
  }
}
