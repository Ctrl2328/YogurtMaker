classDiagram
    direction TB

    %% Definicion de la Interfaz Base de Spring Data
    class JpaRepository {
        <<Interface>>
    }

    %% Capa Repository
    class RecipeRepository {
        <<Repository>>
        +findByName(String name) Optional~Recipe~
        +findByActive(Boolean active) List~Recipe~
        +searchByKeyword(String keyword) List~Recipe~
    }
    %%  adccion de Herencia
    RecipeRepository --|> JpaRepository : extends

    %% Capa Service
    class RecipeService {
        <<Service>>
        -RecipeRepository recipeRepository
        +createRecipe(RecipeDTO recipeDTO) Recipe
        +updateRecipe(Long id, RecipeDTO recipeDTO) Recipe
        +getRecipe(Long id) Recipe
        +getAllActiveRecipes() List~Recipe~
    }
    %% Inyeccion de Dependencias 
    RecipeService --> RecipeRepository : uses (Injected)

    %% Capa Controller
    class RecipeController {
        <<RestController>>
        -RecipeService recipeService
        +createRecipe(RecipeDTO recipeDTO) ResponseEntity~Recipe~
        +getRecipe(Long id) ResponseEntity~Recipe~
        +getAllRecipes() ResponseEntity~List~Recipe~~
    }
    %% Inyección de Dependencias 
    RecipeController --> RecipeService : uses (Injected)

    %% Capa Entity 
    class Recipe {
        <<Entity>>
        -Long id
        -String name
        -String description
        -Double defaultMilkVolume
        -DifficultyLevel difficulty
        -Boolean active
    }

    class Ingredient {
        <<Entity>>
        -Long id
        -String name
        -Double quantity
        -String unit
        -Boolean optional
    }

    %% Relacion de Asociacion y Multiplicidad (OneToMany)
    Recipe "1" *-- "0..*" Ingredient : contains

    %% Dependencias de uso hacia las entidades
    RecipeRepository ..> Recipe : queries
    RecipeService ..> Recipe : processes