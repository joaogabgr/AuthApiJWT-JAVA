package com.auth.jwt.dto.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.EntityModel;

@AllArgsConstructor
@Data
public class ResponseModelDTO extends EntityModel<ResponseModelDTO> {
    public int status;
    public Object model;
    public String error;

    public ResponseModelDTO(Object message) {
        this.status = 200;
        this.model = message;
        this.error = "";
    }
}
