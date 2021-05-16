package com.sanvalero.practicadosspringmayo.controller;

import com.sanvalero.practicadosspringmayo.domain.Location;
import com.sanvalero.practicadosspringmayo.domain.dto.LocationDTO;
import com.sanvalero.practicadosspringmayo.exception.LocationNotFoundException;
import com.sanvalero.practicadosspringmayo.service.LocationService;
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
@Tag(name = "locations", description = "Locations information")
public class LocationController {

    private final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get all location list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "location list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Location.class)))),
            @ApiResponse(responseCode = "404", description = "location list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/farm/locations", produces = "application/json")
    public ResponseEntity<Set<Location>> getLocations() {

        logger.info("Init getLocations");

        Set<Location> locations = locationService.findAll();

        logger.info("End getLocations");

        return ResponseEntity.status(HttpStatus.OK).body(locations);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get location by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location exists",
                    content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "Location doesn't exists",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/farm/locations/{id}", produces = "application/json")
    public ResponseEntity<Location> getLocationById(@PathVariable long id) {

        logger.info("Init getLocations");

        Location location = locationService.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        logger.info("End getLocationById");

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "location added",
                    content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "Location couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value= "/farm/locations", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {

        logger.info("Init addLocation");

        Location addedLocation = locationService.addLocation(location);

        logger.info("End addLocation");

        return new ResponseEntity<>(addedLocation, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "location modified",
                    content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "location doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/farm/locations/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Location> modifyLocation(@PathVariable long id, @RequestBody LocationDTO locationDTO) {

        logger.info("Init modifyLocation");

        Location location = locationService.modifyLocation(id, locationDTO);

        logger.info("End modifyLocation");

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Deletes location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La location doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/farm/locations/{id}")
    public ResponseEntity<Response> deleteLocation(@PathVariable long id) {

        logger.info("Init deleteLocation");

        locationService.deleteLocation(id);

        logger.info("End deleteLocation");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(LocationNotFoundException lnfe){
        Response response = Response.errorResponse(NOT_FOUND, lnfe.getMessage());
        logger.error(lnfe.getMessage(), lnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
