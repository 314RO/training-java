package com.excilys.training.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.MapperComputer;
import com.excilys.training.model.Computer;
import com.excilys.training.service.ComputerService;

@RestController
public class ComputerCRUD {

    @Autowired
    private ComputerService computerService;

    @Autowired
    private MapperComputer mapperComputer;

    // GetByID

    @RequestMapping(value = "/computer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Computer> getComputer(@PathVariable("id") long id) {
        Computer computer = computerService.getById(id);
        if (computer == null) {
            return new ResponseEntity<Computer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Computer>(computer, HttpStatus.OK);
    }

    // Add

    @RequestMapping(value = "/computer/", method = RequestMethod.POST)
    public ResponseEntity<Computer> createcomputer(@RequestBody ComputerDTO computerDTO) {

        computerService.add(mapperComputer.DTOToObj(computerDTO));

        return new ResponseEntity<Computer>(mapperComputer.DTOToObj(computerDTO), HttpStatus.OK);
    }

    // Delete
    @RequestMapping(value = "/computer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Computer> deletecomputer(@PathVariable("id") long id) {

        Computer computer = computerService.getById(id);
        if (computer == null) {
            return new ResponseEntity<Computer>(HttpStatus.NOT_FOUND);
        }
        computerService.delete(id);
        return new ResponseEntity<Computer>(HttpStatus.NO_CONTENT);
    }

}
