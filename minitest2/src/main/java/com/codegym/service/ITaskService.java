package com.codegym.service;

import java.util.Optional;
import com.codegym.model.Task;
public interface ITaskService {
    Iterable<Task> findAll();

    Optional<Task> findById(Long id);
}
