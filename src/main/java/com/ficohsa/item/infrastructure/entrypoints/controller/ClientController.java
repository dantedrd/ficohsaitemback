package com.ficohsa.item.infrastructure.entrypoints.controller;

import com.ficohsa.item.application.usecase.CreateClientUseCase;
import com.ficohsa.item.domain.models.Client;
import com.ficohsa.item.infrastructure.entrypoints.model.ClientRequest;
import com.ficohsa.item.infrastructure.entrypoints.model.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final CreateClientUseCase createClientUseCase;

    /**
     * Creates an instance of {@code ClientController} with necessary useCase.
     *
     * @param createClientUseCase Port for operation calculations.
     */
    public ClientController(CreateClientUseCase createClientUseCase) {
        this.createClientUseCase = createClientUseCase;
    }

    @Operation(
            summary = "created client",
            description = "created client",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "delete client",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Object> createClient(@Valid @RequestBody ClientRequest client){
        Client responseClient=this.createClientUseCase.createClient(client.toDomain());
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(GenericResponse.of(HttpStatus.CREATED.value(),"Cliente creado correctamente",responseClient));
    }

    @Operation(
            summary = "delete account",
            description = "delete account",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "account deleted",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            }
    )
    @DeleteMapping("/{nit}")
    public ResponseEntity<Object> deleteClient(@PathVariable String nit){
        this.createClientUseCase.deleteClient(nit);
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(GenericResponse.of(HttpStatus.CREATED.value(),"Eliminado correctamente",null));
    }

}
