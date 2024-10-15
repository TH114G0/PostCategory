package br.com.crud.repository;

import br.com.crud.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostModel, Long> { }