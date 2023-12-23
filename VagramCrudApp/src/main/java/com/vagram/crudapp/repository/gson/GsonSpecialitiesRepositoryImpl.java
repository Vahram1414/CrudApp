package com.vagram.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vagram.crudapp.model.Specialities;
import com.vagram.crudapp.model.Status;
import com.vagram.crudapp.repository.SpecialityRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GsonSpecialitiesRepositoryImpl implements SpecialityRepository {
    private final Gson GSON = new Gson();
    private final String SPECIALITIES_FILE_LOCATION = "src/main/resources/specialities.json";

    private List<Specialities> getAllSpecialitiesFromFile() {
        try (Reader reader = Files.newBufferedReader(Paths.get(SPECIALITIES_FILE_LOCATION))) {
            return GSON.fromJson(reader, new TypeToken<List<Specialities>>() {
            }.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeSpecialitiesToFile(List<Specialities> a) {
        try {
            FileWriter fileWriter = new FileWriter(SPECIALITIES_FILE_LOCATION);
            Gson gson = new Gson();
            gson.toJson(a, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private Integer generateId(List<Specialities> specialities) {
        return specialities.stream()
                .mapToInt(Specialities::getId)
                .max().orElse(0) + 1;
    }

    public Specialities getById(Integer id) {
        return getAllSpecialitiesFromFile()
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Specialities> getAll() {
        return getAllSpecialitiesFromFile();
    }

    public Specialities save(Specialities specialitiesToSave) throws IOException {
        List<Specialities> specialities = getAllSpecialitiesFromFile();
        Integer id = generateId(specialities);
        specialitiesToSave.setId(id);
        specialities.add(specialitiesToSave);
        writeSpecialitiesToFile(specialities);
        return specialitiesToSave;
    }

    @Override
    public Specialities update(Specialities specialitiesToUpdate) {
        List<Specialities> specialities = getAllSpecialitiesFromFile().stream().peek(speciality -> {
            if (Objects.equals(speciality.getId(), specialitiesToUpdate.getId())) {
                speciality.setName(specialitiesToUpdate.getName());
                speciality.setStatus(specialitiesToUpdate.getStatus());
            }
        }).collect(Collectors.toList());
        writeSpecialitiesToFile(specialities);
        return specialitiesToUpdate;
    }

    public boolean deleteById(Integer integerId) {
        List<Specialities> specialities = getAllSpecialitiesFromFile().stream().peek(speciality -> {
            if (Objects.equals(speciality.getId(), integerId)) {
                speciality.setStatus(Status.DELETED);
            }
        }).collect(Collectors.toList());
        writeSpecialitiesToFile(specialities);
        return true;
    }
}
