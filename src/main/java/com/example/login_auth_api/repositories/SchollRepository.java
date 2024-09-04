package com.example.login_auth_api.repositories;

import com.example.login_auth_api.domain.School;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchollRepository  extends JpaRepository<School, Long> {
    Optional<School> findByName(String name);
    Optional<School> findByCnpj(String cnpj);
    Optional<School> findByEmail(String email);
    @NonNull
    Optional<School> findById(@NonNull Long id);

    void deleteById(Long id);


}
