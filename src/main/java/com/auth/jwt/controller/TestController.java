package com.auth.jwt.controller;

import com.auth.jwt.dto.web.ResponseModelDTO;
import com.auth.jwt.excepition.SystemContextException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/teste/{id}")
    public ResponseEntity<?> test(@PathVariable String id) throws SystemContextException {
        try {
            ResponseModelDTO response = new ResponseModelDTO("Test");
            response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TestController.class).test(id)).withSelfRel());
            response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TestController.class).tests()).withRel("tests"));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            throw new SystemContextException("Error");
        }
    }

    @GetMapping("/testes")
    public ResponseEntity<?> tests() throws SystemContextException {
        try {
            var response = new ResponseModelDTO("Test");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            throw new SystemContextException("Error");
        }
    }
}
