package com.example.TestProject.Controller;

import com.example.TestProject.Model.Characters;
import com.example.TestProject.Repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private final CharacterRepository characterRepository;

    CharacterController(final CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    @GetMapping()
    public Iterable<Characters> allCharacters(){
        return this.characterRepository.findAll();
    }

    @GetMapping("/{id}")
    public Characters showCharacter(@PathVariable Long id){
        Optional<Characters> optionalCharacter = this.characterRepository.findById(id);

        if(optionalCharacter.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Characters characters = optionalCharacter.get();

        return characters;
    }

    @GetMapping("/character")
    public List<Characters> showFromName(@RequestParam(value = "n") String name){
        List<Characters> optionalCharacter = this.characterRepository.findByName(name);

        if(ObjectUtils.isEmpty(optionalCharacter)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return optionalCharacter;
    }

    @GetMapping("/search")
    public List<Characters> searchCharacter(@RequestParam(value = "n") String name, @RequestParam(value ="s") String series){
        List<Characters> optionalCharacter = this.characterRepository.findByNameAndSeries(name, series);

        if(ObjectUtils.isEmpty(optionalCharacter)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return optionalCharacter;
    }

    @GetMapping("/{series}")
    public List<Characters> searchSeries(@PathVariable String series){
        return this.characterRepository.findBySeriesOrderByNameDesc(series);
    }

    @PostMapping()
    public Characters addCharacter(@RequestBody Characters characters){
        characterCreateForm(characters);

       Characters newChar = this.characterRepository.save(characters);

        return newChar;
    }

    @DeleteMapping()
    public Characters removeCharacter(Long id){
        Optional<Characters> optionalCharacters = this.characterRepository.findById(id);

        if(optionalCharacters.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Characters characters = optionalCharacters.get();

        return characters;
    }


    public void characterCreateForm(Characters characters){
        if(ObjectUtils.isEmpty(characters.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(ObjectUtils.isEmpty(characters.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(ObjectUtils.isEmpty(characters.getAge())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(ObjectUtils.isEmpty(characters.getSeries())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(ObjectUtils.isEmpty(characters.getBio())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


}
