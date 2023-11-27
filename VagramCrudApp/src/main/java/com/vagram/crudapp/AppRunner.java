package com.vagram.crudapp;

import com.vagram.crudapp.model.Skill;
import com.vagram.crudapp.model.Status;
import com.vagram.crudapp.repository.SkillRepository;
import com.vagram.crudapp.repository.gson.GsonSkillRepositoryImpl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public class AppRunner {

    public static void main(String[] args) throws IOException {
        SkillRepository skillRepository = new GsonSkillRepositoryImpl();
        List<Skill> skills = skillRepository.getAll();
        System.out.println(skills);

        Skill skill = new Skill();
        skill.setName("T");
        skill.setStatus(Status.ACTIVE);


        Skill createdSkill = skillRepository.save(skill);
        createdSkill.setName("reading2");
        skillRepository.update(createdSkill);
//        Skill skillToUpdate = skillRepository.update(skill);
        System.out.println(createdSkill);
    }
}
