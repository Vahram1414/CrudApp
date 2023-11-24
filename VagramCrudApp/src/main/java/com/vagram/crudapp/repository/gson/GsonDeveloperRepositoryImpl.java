package com.vagram.crudapp.repository.gson;

import com.vagram.crudapp.model.Skill;
import com.vagram.crudapp.repository.SkillRepository;

import java.io.IOException;
import java.util.List;

public class GsonDeveloperRepositoryImpl implements SkillRepository {
    @Override
    public Skill getById(Integer integer) {
        return null;
    }

    @Override
    public List<Skill> getAll() {
        return null;
    }

    @Override
    public Skill save(Skill skill) throws IOException {
        return null;
    }

    @Override
    public Skill update(Skill skill) {
        return null;
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }
}
