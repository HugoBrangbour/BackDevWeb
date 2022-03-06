package com.bnm.controller;

import com.bnm.entity.CarteForm;
import com.bnm.entity.CarteMagic;
import com.bnm.repository.CarteMagicRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/CarteMagic")
public class CarteMagicController {
    private final CarteMagicRepository repository;

    public CarteMagicController(CarteMagicRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get toutes les cartes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Les carte ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = CarteMagic.class
                            ))})
    })
    @GetMapping("/")
    public ResponseEntity<Iterable<CarteMagic>> getALL() {
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get la carte par son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La carte a été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarteMagic.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Carte non existante",
                    content = @Content)
    })
    @GetMapping("/{idCarte}")
    public ResponseEntity<CarteMagic> getById(
            @Parameter(description = "Id de la carte")
            @PathVariable("idCarte") Integer idCarte) {
        Optional<CarteMagic> carte = repository.findById(idCarte);
        if (carte.isPresent()) {
            return new ResponseEntity<>(carte.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Créé une nouvelle carte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "la carte a été créé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarteMagic.class
                            ))})
    })
    @PostMapping("")
    public ResponseEntity<CarteMagic> createCarte(
            @Parameter(description = "La nouvelle carte à enregistrer")
                @RequestBody CarteForm newCarte) {
        CarteMagic carte = repository.save(new CarteMagic(newCarte));
        return new ResponseEntity<>(carte, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifie cette carte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "la carte a été modifié",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarteMagic.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Carte non existante",
                    content = @Content)
    })
    @PutMapping("/{idCarte}")
    public ResponseEntity<CarteMagic> updateCarte(
            @Parameter(description = "Id de la carte")
                @PathVariable("idCarte") Integer idCarte,
            @Parameter(description = "la carte modifié")
                @RequestBody CarteForm modifiedCarte)    {
        if (repository.existsById(idCarte)){
            CarteMagic carteTemp = new CarteMagic(modifiedCarte);
            carteTemp.setId(idCarte);
            CarteMagic newCarte = repository.save(carteTemp);
            return new ResponseEntity<>(newCarte, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Supprime cette carte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La carte a été supprimé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Carte non existante",
                    content = @Content)
    })
    @DeleteMapping("/{idCarte}")
    public ResponseEntity<Integer> deleteCarte(
            @Parameter(description = "Id de la carte")
            @PathVariable("idCarte") Integer idCarte) {
        if (repository.existsById(idCarte)){
            repository.deleteById(idCarte);
            return new ResponseEntity<>(idCarte, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Get toutes les cartes qui ont ce nom dans leur titre ou alors qui contient ce nom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "les cartes ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = CarteMagic.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Carte non existante",
                    content = @Content)
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<Iterable<CarteMagic>> getByName(
            @Parameter(description = "Nom exact ou contenat ce nom dans le titre de la carte")
            @PathVariable("name") String name) {
        Set<CarteMagic> listeCarte = repository.findByNameIgnoreCase(name);
        if(listeCarte.isEmpty()){
            listeCarte = repository.findByNameContainsIgnoreCase(name);
            if(listeCarte.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(listeCarte, HttpStatus.OK);
    }

    @Operation(summary = "Get toutes les cartes qui ont ce coût converti précisément")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "les cartes ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = CarteMagic.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Carte non existante",
                    content = @Content)
    })
    @GetMapping("/ConvertedManaCost/{cmc}")
    public ResponseEntity<Iterable<CarteMagic>> getByCMC(
            @Parameter(description = "Coût converti de la/des cartes")
            @PathVariable("cmc") Integer cmc) {
        Set<CarteMagic> listeCarte = repository.findByConvertedManaCost(cmc);
        if(listeCarte.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listeCarte, HttpStatus.OK);
    }

    @Operation(summary = "Get toutes les cartes qui ont ce coût en mana précis ou qui contient ce coût")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "les cartes ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = CarteMagic.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Carte non existante",
                    content = @Content)
    })
    @GetMapping("/mana/{mana}")
    public ResponseEntity<Iterable<CarteMagic>> getByManaCost(
            @Parameter(description = "Coût de mana de la carte à trouvé")
            @PathVariable("mana") String mana) {
        Set<CarteMagic> listeCarte = repository.findByManaCostIgnoreCase(mana);
        if(listeCarte.isEmpty()){
            listeCarte = repository.findByManaCostContainsIgnoreCase(mana);
            if(listeCarte.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(listeCarte, HttpStatus.OK);
    }

    @Operation(summary = "Get toutes les cartes qui sont de cette couleur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "les cartes ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = CarteMagic.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Carte non existante",
                    content = @Content)
    })
    @GetMapping("/color/{color}")
    public ResponseEntity<Iterable<CarteMagic>> getByColor(
            @Parameter(description = "Couleur de la/des cartes")
            @PathVariable("color") String color) {
        Set<CarteMagic> listeCarte = repository.findByColorsIgnoreCase(color);
        if(listeCarte.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listeCarte, HttpStatus.OK);
    }

    @Operation(summary = "Get toutes les cartes qui ont ce type dans leurs types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "les cartes ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = CarteMagic.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Carte non existante",
                    content = @Content)
    })
    @GetMapping("/type/{type}")
    public ResponseEntity<Iterable<CarteMagic>> getByType(
            @Parameter(description = "type de la/des cartes")
            @PathVariable("type") String type) {
        Set<CarteMagic> listeCarte = repository.findByTypeContainingIgnoreCase(type);
        if(listeCarte.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listeCarte, HttpStatus.OK);
    }

    @Operation(summary = "Get toutes les cartes qui ont ce texte précis ou qui contient ce texte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "les cartes ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = CarteMagic.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Carte non existante",
                    content = @Content)
    })
    @GetMapping("/text/{text}")
    public ResponseEntity<Iterable<CarteMagic>> getByText(
            @Parameter(description = "Texte de la/des cartes")
            @PathVariable("text") String text) {
        Set<CarteMagic> listeCarte = repository.findByTextContainsIgnoreCase(text);
        if(listeCarte.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listeCarte, HttpStatus.OK);
    }

    @Operation(summary = "Get toutes les cartes qui ont cette puissance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "les cartes ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = CarteMagic.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Carte non existante",
                    content = @Content)
    })
    @GetMapping("/power/{power}")
    public ResponseEntity<Iterable<CarteMagic>> getByPower(
            @Parameter(description = "Puissance de la/des cartes")
            @PathVariable("power") String power) {
        Set<CarteMagic> listeCarte = repository.findByPowerIgnoreCase(power);
        if(listeCarte.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listeCarte, HttpStatus.OK);
    }

    @Operation(summary = "Get toutes les cartes qui ont cette endurance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "les cartes ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = CarteMagic.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Carte non existante",
                    content = @Content)
    })
    @GetMapping("/toughness/{toughness}")
    public ResponseEntity<Iterable<CarteMagic>> getByToughness(
            @Parameter(description = "Endurance de la/des cartes")
            @PathVariable("toughness") String toughness) {
        Set<CarteMagic> listeCarte = repository.findByToughnessIgnoreCase(toughness);
        if(listeCarte.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listeCarte, HttpStatus.OK);
    }
}
