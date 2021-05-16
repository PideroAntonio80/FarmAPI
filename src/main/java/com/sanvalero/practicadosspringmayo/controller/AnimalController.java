package com.sanvalero.practicadosspringmayo.controller;

import com.sanvalero.practicadosspringmayo.domain.Animal;
import com.sanvalero.practicadosspringmayo.domain.dto.AnimalDTO;
import com.sanvalero.practicadosspringmayo.exception.AnimalNotFoundException;
import com.sanvalero.practicadosspringmayo.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.practicadosspringmayo.controller.Response.NOT_FOUND;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@RestController
@Tag(name = "Animals", description = "Animals information")
public class AnimalController {

    private final Logger logger = LoggerFactory.getLogger(AnimalController.class);

    @Autowired
    private AnimalService animalService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get al animals list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animals list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Animal.class)))),
            @ApiResponse(responseCode = "404", description = "Animals list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/farm/animals", produces = "application/json")
    public ResponseEntity<Set<Animal>> getAnimals() {

        logger.info("Init getAnimals");

        Set<Animal> animals = animalService.findAll();

        logger.info("End getAnimals");

        return ResponseEntity.status(HttpStatus.OK).body(animals);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get animal by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal exist",
                    content = @Content(schema = @Schema(implementation = Animal.class))),
            @ApiResponse(responseCode = "404", description = "Animal doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/farm/animals/{id}", produces = "application/json")
    public ResponseEntity<Animal> getAnimalById(@PathVariable long id) {

        logger.info("Init getAnimalById");

        Animal animal = animalService.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));

        logger.info("End getAnimalById");

        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new animal into a farm")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Animal added",
                    content = @Content(schema = @Schema(implementation = Animal.class))),
            @ApiResponse(responseCode = "404", description = "Animal couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/farm/farms/{id}/animals", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Animal> addAnimal(@PathVariable long id, @RequestBody AnimalDTO animalDTO) {

        logger.info("Init addAnimal");

        Animal addedAnimal = animalService.addAnimalToFarm(id, animalDTO);

        logger.info("End addAnimal");

        return new ResponseEntity<>(addedAnimal, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify animal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal modified",
                    content = @Content(schema = @Schema(implementation = Animal.class))),
            @ApiResponse(responseCode = "404", description = "Animal doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/farm/animals/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Animal> modifyAnimal(@PathVariable long id, @RequestBody AnimalDTO animalDTO) {

        logger.info("Init modifyAnimal");

        Animal animal = animalService.modifyAnimal(id, animalDTO);

        logger.info("End modifyAnimal");

        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    /*--------------------------------MODIFY_BY_WEIGHT----------------------------------*/
    @Operation(summary = "Modify animal by weight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal weight modified",
                    content = @Content(schema = @Schema(implementation = Animal.class))),
            @ApiResponse(responseCode = "404", description = "Animal doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/farm/animals/{id}/weight", produces = "application/json")
    public ResponseEntity<Animal> modifyAnimalByWeight(@PathVariable long id,
                                                          @RequestParam(value = "weight", defaultValue = "") float weight) {

        logger.info("Init modifyAnimalByWeight");

        Animal animal = animalService.modifyAnimalByWeight(id, weight);

        logger.info("End modifyAnimalByWeight");

        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Delete animal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Animal doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/farm/animals/{id}")
    public ResponseEntity<Response> deleteAnimal(@PathVariable long id) {

        logger.info("Init deleteAnimal");

        animalService.deleteAnimal(id);

        logger.info("End deleteAnimal");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(AnimalNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(AnimalNotFoundException anfe){
        Response response = Response.errorResponse(NOT_FOUND, anfe.getMessage());
        logger.error(anfe.getMessage(), anfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
