package com.vagram.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vagram.crudapp.model.Skill;
import com.vagram.crudapp.model.Status;
import com.vagram.crudapp.repository.SkillRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GsonSkillRepositoryImpl implements SkillRepository {
    private final Gson GSON = new Gson();
    private final String SKILL_FILE_LOCATION = "src/main/resources/skills.json";

    private List<Skill> getAllSkillsFromFile() {
        try (Reader reader = Files.newBufferedReader(Paths.get(SKILL_FILE_LOCATION))) {
            return GSON.fromJson(reader, new TypeToken<List<Skill>>() {
            }.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeSkillsToFile(List<Skill> a) {
        try {
            FileWriter fileWriter = new FileWriter(SKILL_FILE_LOCATION);
            Gson gson = new Gson();
            gson.toJson(a, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
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

    @Override
    public Skill update(Skill skillToUpdate) {
        List<Skill> skills = getAllSkillsFromFile().stream().peek(skill -> {
            if (Objects.equals(skill.getId(), skillToUpdate.getId())) {
                skill.setName(skillToUpdate.getName());
                skill.setStatus(skillToUpdate.getStatus());
            }
        }).collect(Collectors.toList());
        writeSkillsToFile(skills);
        return skillToUpdate;
    }

    public boolean deleteById(Integer integerId) {
        List<Skill> skills = getAllSkillsFromFile().stream().peek(skill -> {
            if (Objects.equals(skill.getId(), integerId)) {
                skill.setStatus(Status.DELETED);
            }
        }).collect(Collectors.toList());
        writeSkillsToFile(skills);
        return true;
    }
}


