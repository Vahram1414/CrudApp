package com.vagram.crudapp;

import com.vagram.crudapp.model.Developer;
import com.vagram.crudapp.model.Skill;
import com.vagram.crudapp.model.Specialities;
import com.vagram.crudapp.model.Status;
import com.vagram.crudapp.repository.DeveloperRepository;
import com.vagram.crudapp.repository.SkillRepository;
import com.vagram.crudapp.repository.SpecialityRepository;
import com.vagram.crudapp.repository.gson.GsonDeveloperRepositoryImpl;
import com.vagram.crudapp.repository.gson.GsonSkillRepositoryImpl;
import com.vagram.crudapp.repository.gson.GsonSpecialitiesRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AppRunner {

    public static void main(String[] args) throws IOException {
        SkillRepository skillRepository = new GsonSkillRepositoryImpl();
        List<Skill> skills = skillRepository.getAll();
        System.out.println(skills);

        Skill skill = new Skill();
        skill.setName("Y");
        skill.setStatus(Status.DELETED);


        Skill createdSkill = skillRepository.save(skill);
        createdSkill.setName("reading3");
        skillRepository.update(createdSkill);
        System.out.println(createdSkill);

        skillRepository.deleteById(25);

        DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();
//        List<Developer> developers = developerRepository.getAll();
//        System.out.println(developers);

        List<Skill> skilldev = new ArrayList<>();
        skilldev.add(createdSkill);
        Developer developer = new Developer();
        developer.setSkills(skilldev);
        developer.setFirstName("Mike");
        developer.setLastName("Stevenson");

        Developer createdDeveloper = developerRepository.save(developer);
        createdDeveloper.setFirstName("John");
        createdDeveloper.setLastName("Tompson");
        developerRepository.update(createdDeveloper);
        System.out.println(createdDeveloper);

        developerRepository.deleteById(14);

        SpecialityRepository specialityRepository = new GsonSpecialitiesRepositoryImpl();
        List<Specialities> specialities = specialityRepository.getAll();
        System.out.println(specialities);

        Specialities speciality = new Specialities();
        speciality.setName(" ");
        speciality.setStatus(Status.ACTIVE);

        Specialities createdSpeciality = specialityRepository.save(speciality);
        System.out.println(specialities);
    }
}
