package com.example.TestProject.Repository;

import com.example.TestProject.Model.Characters;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CharacterRepository extends CrudRepository <Characters, Long> {

    List<Characters> findByNameAndSeries(String name, String series);
    List<Characters> findBySeriesOrderByNameDesc(String series);

    List<Characters> findByName(String name);


}
