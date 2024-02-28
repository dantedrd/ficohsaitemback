package com.ficohsa.item.infrastructure.entrypoints.controller;

import com.ficohsa.item.application.usecase.ItemUseCase;
import com.ficohsa.item.domain.models.Item;
import com.ficohsa.item.infrastructure.entrypoints.model.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemUseCase itemUseCase;

    /**
     * Constructs a new {@code ItemController} instance with the provided {@code ItemUseCase}.
     * This controller is responsible for handling operations related to items.
     *
     * @param itemUseCase The use case instance responsible for item-related operations.
     */
    public ItemController(ItemUseCase itemUseCase) {
        this.itemUseCase = itemUseCase;
    }

    @Operation(
            summary = "Search Items",
            description = "Search items by name",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Items found successfully",
                            content = @Content(schema = @Schema(implementation = ResponseEntity.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Items not found"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    @GetMapping("/search")
    public ResponseEntity<Object> searchItems(@RequestParam(name = "name") String name,@RequestParam(name = "total") Integer total){
        List<Item> responseItems=this.itemUseCase.searchItems(name,total);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.of(HttpStatus.OK.value(),"Items encontrados correctamente",responseItems));
    }


    @Operation(
            summary = "Buscar item por ID",
            description = "Buscar un item por su ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Item encontrado con éxito",
                            content = @Content(schema = @Schema(implementation = GenericResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Item no encontrado",
                            content = @Content(schema = @Schema(implementation = GenericResponse.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> getItem(@PathVariable() Long id){
        Item responseItem=this.itemUseCase.getItemById(id);
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(GenericResponse.of(HttpStatus.OK.value(),"Item encontrado con exito",responseItem));
    }
}
