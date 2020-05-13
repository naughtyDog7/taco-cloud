package com.example.tacoapi.dao;

import com.example.tacoapi.model.Taco;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(value = "http://localhost:8089", methods = {GET, POST})
@RepositoryRestResource()
public interface TacoDAO extends CrudRepository<Taco, Long> {
    List<Taco> findAll(Pageable pageable);
//    Taco findOne(int id);
//    void save(Taco taco);
//    void update(Taco taco);
//    void delete(Taco taco);
}
