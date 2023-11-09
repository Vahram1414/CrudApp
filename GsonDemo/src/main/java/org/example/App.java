package org.example;

import static org.example.enums.Status.ACTIVE;

import org.example.dto.Skill;
import org.example.repository.impl.GsonSkillRepositoryImpl;

public class App {

  public static void main(String[] args) {
    var repository = new GsonSkillRepositoryImpl();
    System.out.println(repository.findAll()); // пусто (обязательно наличие skills.json с пустым содержимым [], либо делать много проверок)

    for (int i = 0; i < 7; i++) {
      var skill = new Skill();
      skill.setName("skill" + i);
      skill.setStatus(ACTIVE);
      repository.save(skill);
    }
    System.out.println(repository.findAll()); // созданы 6 скиллов

    var skill = new Skill();
    skill.setId(3L);
    skill.setName("edited skill3");
    skill.setStatus(ACTIVE);
    repository.save(skill);
    System.out.println(repository.findAll()); // отредактирован 3й скилл

    repository.deleteById(1L);
    System.out.println(repository.findAll()); // 1й скилл DELETED
  }
}
