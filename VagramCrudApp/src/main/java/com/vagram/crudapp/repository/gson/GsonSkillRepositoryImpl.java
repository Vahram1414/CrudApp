package com.vagram.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vagram.crudapp.model.Skill;
import com.vagram.crudapp.model.Status;
import com.vagram.crudapp.repository.SkillRepository;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GsonSkillRepositoryImpl implements SkillRepository {
    private final Gson GSON = new Gson();
    private final String SKILL_FILE_LOCATION = "src/main/resources/skills.json";
    private static List<Skill> theList;

    private List<Skill> getAllSkillsFromFile() {
        try (Reader reader = Files.newBufferedReader(Paths.get(SKILL_FILE_LOCATION))) {
            return GSON.fromJson(reader, new TypeToken<List<Skill>>() {
            }.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeSkillsToFile(List<Skill> a) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(SKILL_FILE_LOCATION);
            Gson gson = new Gson();
            gson.toJson(a, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }

        //TODO: write json string to file
    }

    private Integer generateId(List<Skill> skills) {
        return skills.stream()
                .mapToInt(Skill::getId)
                .max().orElse(0) + 1;
    }

    public Skill getById(Integer id) {
        return getAllSkillsFromFile()
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Skill> getAll() {
        return getAllSkillsFromFile();
    }

    public Skill save(Skill skillToSave) throws IOException {
        List<Skill> skills = getAllSkillsFromFile();
        Integer id = generateId(skills);
        skillToSave.setId(id);
        skills.add(skillToSave);
        writeSkillsToFile(skills);
        return skillToSave;
    }

    public Skill update(Skill skillToUpdate) throws IOException {
        List<Skill> skills = getAllSkillsFromFile();
        int indexSkill = skillToUpdate.getId() - 1;
        skills.remove(indexSkill);
        skills.add(skillToUpdate);
        writeSkillsToFile(skills);
        return skillToUpdate;
    }

    public boolean deleteById(Integer integerId) {
        List<Skill> skills = getAllSkillsFromFile();
        Skill skill = skills.get(integerId - 1);
        skill.setStatus(Status.DELETED);
        try {
            writeSkillsToFile(skills);
        } catch (IOException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return true;
    }
}
