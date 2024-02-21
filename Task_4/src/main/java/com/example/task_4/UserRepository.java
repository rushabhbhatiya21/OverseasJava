package com.example.task_4;

import com.example.task_4.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
}
