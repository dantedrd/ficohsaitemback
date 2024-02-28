package com.ficohsa.item.infrastructure.entrypoints.controller;

import com.ficohsa.item.application.usecase.AccountUseCase;
import com.ficohsa.item.domain.models.Account;
import com.ficohsa.item.infrastructure.entrypoints.model.AccountRequest;
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

import java.util.Objects;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    AccountUseCase accountUseCase;

    public AccountController(AccountUseCase accountUseCase) {
        this.accountUseCase = accountUseCase;
    }


    @Operation(
            summary = "Create account",
            description = "Create account",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "account created",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Object> createClient(@Valid @RequestBody AccountRequest account){
        Account responseAccount=this.accountUseCase.createAccount(account.toDomain());
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(GenericResponse.of(HttpStatus.CREATED.value(),"Cuenta creada correctamente",responseAccount));
    }

    @Operation(
            summary = "update account",
            description = "update account",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "update account",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Object> updateAccount(@Valid @RequestBody AccountRequest account){
        Account responseAccount=this.accountUseCase.findAccountById(account.getAccountNumber());
        responseAccount.setState(account.getStateAccount(account.getState()));
        responseAccount.setGMFExempt(Objects.nonNull(account.getGMFExempt())?account.getGMFExempt():responseAccount.getGMFExempt());
        Account responseUpdatedAccount=this.accountUseCase.updatedAccount(responseAccount);
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(GenericResponse.of(HttpStatus.CREATED.value(),"Cuenta creada correctamente",responseUpdatedAccount));
    }

    @Operation(
            summary = "delete account",
            description = "delete account",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "delete account",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    )
            }
    )
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Object> deleteAccount(@PathVariable String accountNumber){
        this.accountUseCase.deleteAccount(accountNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(GenericResponse.of(HttpStatus.CREATED.value(),"cuenta eliminada correctamente",null));
    }
}
