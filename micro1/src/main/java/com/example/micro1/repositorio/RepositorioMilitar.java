package com.example.micro1.repositorio;

import com.example.micro1.modelo.Militar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


public interface RepositorioMilitar extends MongoRepository<Militar,String> {
}
