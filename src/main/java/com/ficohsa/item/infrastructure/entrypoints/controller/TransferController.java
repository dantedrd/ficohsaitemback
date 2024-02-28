package com.ficohsa.item.infrastructure.entrypoints.controller;

import com.ficohsa.item.application.usecase.TransferUseCase;
import com.ficohsa.item.domain.models.Transfer;
import com.ficohsa.item.infrastructure.entrypoints.model.GenericResponse;
import com.ficohsa.item.infrastructure.entrypoints.model.TransferRequest;
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
@RequestMapping("/transfer")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
public class TransferController {
    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

    private final TransferUseCase transferUseCase;

    /**
     * Creates an instance of {@code TransferController} with necessary useCase.
     *
     * @param transferUseCase Port for operation calculations.
     */
    public TransferController(TransferUseCase transferUseCase) {
        this.transferUseCase = transferUseCase;
    }

    @Operation(
            summary = "procces transfer",
            description = "procces transfer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "procces transfer",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Object> proccesTransfer(@Valid @RequestBody TransferRequest transferRequest){
        Transfer responseTransfer=this.transferUseCase.proccess(transferRequest.toDomain());
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(GenericResponse.of(HttpStatus.CREATED.value(),"transferencia creado correctamente",responseTransfer));
    }


}
