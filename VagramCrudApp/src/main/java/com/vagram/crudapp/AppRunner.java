package com.vagram.crudapp;

import com.vagram.crudapp.model.Developer;
import com.vagram.crudapp.model.Skill;
import com.vagram.crudapp.model.Status;
import com.vagram.crudapp.repository.DeveloperRepository;
import com.vagram.crudapp.repository.SkillRepository;
import com.vagram.crudapp.repository.gson.GsonDeveloperRepositoryImpl;
import com.vagram.crudapp.repository.gson.GsonSkillRepositoryImpl;

import java.io.IOException;
import java.util.List;


public class AppRunner {

    public static void main(String[] args) throws IOException {
        SkillRepository skillRepository = new GsonSkillRepositoryImpl();
        List<Skill> skills = skillRepository.getAll();
        System.out.println(skills);

        Skill skill = new Skill();
        skill.setName("S");
        skill.setStatus(Status.ACTIVE);


        Skill createdSkill = skillRepository.save(skill);
        createdSkill.setName("reading2");
        skillRepository.update(createdSkill);
        System.out.println(createdSkill);

        skillRepository.deleteById(2);

        DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();
        List<Developer> developers = developerRepository.getAll();
        System.out.println(developers);


    }
}
