package com.vagram.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vagram.crudapp.model.Developer;
import com.vagram.crudapp.model.Status;
import com.vagram.crudapp.repository.DeveloperRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {
    private final Gson GSON = new Gson();
    private final String DEVELOPER_FILE_LOCATION = "src/main/resources/developer.json";

    private List<Developer> getAllDevelopersFromFile() {
        try (Reader reader = Files.newBufferedReader(Paths.get(DEVELOPER_FILE_LOCATION))) {
            return GSON.fromJson(reader, new TypeToken<List<Developer>>() {
            }.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeDevelopersToFile(List<Developer> a) {
        try {
            FileWriter fileWriter = new FileWriter(DEVELOPER_FILE_LOCATION);
            Gson gson = new Gson();
            gson.toJson(a, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private Integer generateId(List<Developer> developers) {
        return developers.stream()
                .mapToInt(Developer::getId)
                .max().orElse(0) + 1;
    }

    public Developer getById(Integer id) {
        return getAllDevelopersFromFile()
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Developer> getAll() {
        return getAllDevelopersFromFile();
    }

    public Developer save(Developer developerToSave) throws IOException {
        List<Developer> developers = getAllDevelopersFromFile();
        Integer id = generateId(developers);
        developerToSave.setId(id);
        developers.add(developerToSave);
        writeDevelopersToFile(developers);
        return developerToSave;
    }

    public Developer update(Developer developerToUpdate) {
        List<Developer> developers = getAllDevelopersFromFile().stream().peek(developer -> {
            if (Objects.equals(developer.getId(), developerToUpdate.getId())) {
                developer.setFirstName(developerToUpdate.getFirstName());
                developer.setStatus(developerToUpdate.getStatus());
            }
        }).collect(Collectors.toList());
        writeDevelopersToFile(developers);
        return developerToUpdate;
    }

    public boolean deleteById(Integer integerId) {
        List<Developer> developers = getAllDevelopersFromFile().stream().peek(developer -> {
            if (Objects.equals(developer.getId(), integerId)) {
                developer.setStatus(Status.DELETED);
            }
        }).collect(Collectors.toList());
        writeDevelopersToFile(developers);
        return true;
    }
}
