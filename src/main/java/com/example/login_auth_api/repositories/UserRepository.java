package com.example.login_auth_api.repositories;

import com.example.login_auth_api.domain.user.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @NonNull
    Optional<User> findById(@NonNull Long id);
}
