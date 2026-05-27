package com.migueljaramillo.demo.domain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.migueljaramillo.demo.domain.model.Recipe;
import com.migueljaramillo.demo.domain.service.RecipeService;
import com.migueljaramillo.demo.dto.RecipeDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@Tag(name = "1. Gestión de Recetas", description = "Endpoints para crear, buscar y administrar las recetas de yogurt")
public class RecipeController {
    
    private final RecipeService recipeService;
    
    @Operation(summary = "Crear una nueva receta", description = "Registra una nueva receta en el sistema con sus ingredientes y tiempos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Receta creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = recipeService.createRecipe(recipeDTO);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Actualizar una receta", description = "Modifica los datos de una receta existente usando su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = recipeService.updateRecipe(id, recipeDTO);
        return ResponseEntity.ok(recipe);
    }
    
    @Operation(summary = "Obtener receta por ID", description = "Busca y retorna los detalles de una receta específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        return ResponseEntity.ok(recipe);
    }
    
    @Operation(summary = "Listar todas las recetas", description = "Retorna una lista con todas las recetas activas del sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllActiveRecipes());
    }
    
    @Operation(summary = "Buscar recetas", description = "Busca recetas por palabra clave en su nombre o descripción.")
    @ApiResponse(responseCode = "200", description = "Búsqueda completada exitosamente")
    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam String keyword) {
        return ResponseEntity.ok(recipeService.searchRecipes(keyword));
    }
    
    @Operation(summary = "Desactivar una receta", description = "Cambia el estado de una receta a inactiva (Soft delete).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta desactivada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada", content = @Content)
    })
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateRecipe(@PathVariable Long id) {
        recipeService.deactivateRecipe(id);
        return ResponseEntity.ok().build();
    }
    
    @Operation(summary = "Activar una receta", description = "Restaura una receta previamente desactivada.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta activada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada", content = @Content)
    })
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateRecipe(@PathVariable Long id) {
        recipeService.activateRecipe(id);
        return ResponseEntity.ok().build();
    }
}