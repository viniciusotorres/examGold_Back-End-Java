package com.example.login_auth_api.repositories;

import com.example.login_auth_api.domain.user.Scholl;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchollRepository  extends JpaRepository<Scholl, Long> {
    Optional<Scholl> findByName(String name);
    Optional<Scholl> findByCnpj(String cnpj);
    Optional<Scholl> findByEmail(String email);
    @NonNull
    Optional<Scholl> findById(@NonNull Long id);

    void deleteById(Long id);


}
