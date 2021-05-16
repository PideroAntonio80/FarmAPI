package com.sanvalero.practicadosspringmayo.controller;

import com.sanvalero.practicadosspringmayo.domain.Farm;
import com.sanvalero.practicadosspringmayo.domain.dto.FarmDTO;
import com.sanvalero.practicadosspringmayo.exception.FarmNotFoundException;
import com.sanvalero.practicadosspringmayo.service.FarmService;
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
@Tag(name = "farms", description = "Farms information")
public class FarmController {

    private final Logger logger = LoggerFactory.getLogger(FarmController.class);

    @Autowired
    private FarmService farmService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get al farms list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Farm list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Farm.class)))),
            @ApiResponse(responseCode = "404", description = "Farm list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/farm/farms", produces = "application/json")
    public ResponseEntity<Set<Farm>> getFarms() {

        logger.info("Init getFarms");

        Set<Farm> farms = farmService.findAll();

        logger.info("End getFarms");

        return ResponseEntity.status(HttpStatus.OK).body(farms);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get farm by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Farm exist",
                    content = @Content(schema = @Schema(implementation = Farm.class))),
            @ApiResponse(responseCode = "404", description = "Farm doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/farm/farms/{id}", produces = "application/json")
    public ResponseEntity<Farm> getFarmById(@PathVariable long id) {

        logger.info("Init getFarmById");

        Farm farm = farmService.findById(id)
                .orElseThrow(() -> new FarmNotFoundException(id));

        logger.info("End getFarmById");

        return new ResponseEntity<>(farm, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new farm into a location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Farm added",
                    content = @Content(schema = @Schema(implementation = Farm.class))),
            @ApiResponse(responseCode = "404", description = "Farm couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/farm/locations/{id}/farm", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Farm> addFarm(@PathVariable long id, @RequestBody FarmDTO farmDTO) {

        logger.info("Init addFarm");

        Farm addedFarm = farmService.addFarmToLocation(id, farmDTO);

        logger.info("End addFarm");

        return new ResponseEntity<>(addedFarm, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify farm")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Farm modified",
                    content = @Content(schema = @Schema(implementation = Farm.class))),
            @ApiResponse(responseCode = "404", description = "Farm doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/farm/farms/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Farm> modifyFarm(@PathVariable long id, @RequestBody FarmDTO farmDTO) {

        logger.info("Init modifyFarm");

        Farm farm = farmService.modifyFarm(id, farmDTO);

        logger.info("End modifyFarm");

        return new ResponseEntity<>(farm, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Delete farm")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Farm deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Farm doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/farm/farms/{id}")
    public ResponseEntity<Response> deleteFarm(@PathVariable long id) {

        logger.info("Init deleteFarm");

        farmService.deleteFarm(id);

        logger.info("End deleteFarm");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(FarmNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(FarmNotFoundException fnfe){
        Response response = Response.errorResponse(NOT_FOUND, fnfe.getMessage());
        logger.error(fnfe.getMessage(), fnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
