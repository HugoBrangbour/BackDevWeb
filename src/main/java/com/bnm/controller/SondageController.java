package com.bnm.controller;

import com.bnm.entity.Sondage;
import com.bnm.entity.SondageForm;
import com.bnm.repository.SondageRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

/**
 * The type Sondage controller.
 */
@RestController
@RequestMapping("/sondage")
public class SondageController {

    private final SondageRepository sondageRepository;

    /**
     * Instantiates a new Sondage controller.
     *
     * @param sondageRepository the sondage repository
     */
    public SondageController(SondageRepository sondageRepository) {
        this.sondageRepository = sondageRepository;
    }

    /**
     * Gets all.
     *
     * @return all
     */
    @Operation(summary = "Get tout les sondages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tout les sondages ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = Sondage.class
                            ))}) })
    @GetMapping("/")
    public ResponseEntity<Iterable<Sondage>> getAll() {
        return new ResponseEntity<>(sondageRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Gets by nom.
     *
     * @param nom the nom
     * @return Sondage
     */
    @Operation(summary = "Get tout les sondages en fonction du nom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tout les sondages ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = Sondage.class
                            ))}) })
    @GetMapping("/nom/{nom}")
    public ResponseEntity<Iterable<Sondage>> getByNom(
            @Parameter(description = "Le nom de sondage concerné")
                @PathVariable String nom) {
        return new ResponseEntity<>(sondageRepository.findByNom(nom), HttpStatus.OK);
    }

    /**
     * Gets all opened.
     *
     * @param ouvert the ouvert
     * @return all Sondage
     */
    @Operation(summary = "Get tout les sondages en fonction de s'ils sont ouvert ou non")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tout les sondages ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = Sondage.class
                            ))}) })
    @GetMapping("/ouvet/{ouvert}")
    public ResponseEntity<Iterable<Sondage>> getAllOpened(
            @Parameter(description = "Booléen pour savoir s'il faut récupérer les sondages ouvert ou non")
                @PathVariable boolean ouvert) {
        return new ResponseEntity<>(sondageRepository.findByOuvert(ouvert), HttpStatus.OK);
    }

    /**
     * Gets by id.
     *
     * @param sondageId the sondage id
     * @return the Sondage
     */
    @Operation(summary = "Get un sondange en fonction d'un id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Les sondage a été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sondage.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Sondage non existant",
                                    content = @Content)
            })
    @GetMapping("/{sondageId}")
    public ResponseEntity<Sondage> getById(
            @Parameter(description = "L'id du sondage concerné")
                @PathVariable Integer sondageId) {
        Optional<Sondage> sondage = sondageRepository.findById(sondageId);
        if (sondage.isPresent()) {
            return new ResponseEntity<>(sondage.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create sondage response entity.
     *
     * @param newSondage the new sondage
     * @return the response entity
     */
    @Operation(summary = "Créer un nouveau sondage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Le sondage a été créé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sondage.class)) })})
    @PostMapping("/")
    public ResponseEntity<Sondage> createSondage(
            @Parameter(description = "Le sondage a créer")
                @RequestBody SondageForm newSondage) {
        Sondage create = createSondageFromForm(newSondage);
        Sondage sondage = sondageRepository.save(create);
        return new ResponseEntity<>(sondage, HttpStatus.CREATED);
    }

    /**
     * Update sondage response entity.
     *
     * @param sondageId     the sondage id
     * @param updateSondage the update sondage
     * @return the response entity
     */
    @Operation(summary = "Modifie un sondange")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Le sondage a été modifié",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sondage.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Sondage non existant",
                    content = @Content)
    })
    @PutMapping("/{sondageId}")
    public ResponseEntity<Sondage> updateSondage(
            @Parameter(description = "Id du sondage a modifier")
                @PathVariable("sondageId") Integer sondageId,
            @Parameter(description = "Le sondage modifié")
                @RequestBody SondageForm updateSondage)    {
        if (sondageRepository.existsById(sondageId)){
            sondageRepository.deleteById(sondageId);
            Sondage create = createSondageFromForm(updateSondage);
            Sondage sondage = sondageRepository.save(create);
            return new ResponseEntity<>(sondage, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Close sondage response entity.
     *
     * @param sondageId the sondage id
     * @return the response entity
     */
    @Operation(summary = "Ferme un sondange")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Le sondage a été fermé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sondage.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Sondage non existant",
                    content = @Content)
    })
    @PostMapping("/{sondageId}")
    public ResponseEntity<Sondage> closeSondage(
            @Parameter(description = "Id du sondage a fermer")
                @PathVariable("sondageId") Integer sondageId)    {
        if (sondageRepository.existsById(sondageId)){
            Sondage sondage = sondageRepository.getById(sondageId);
            sondageRepository.deleteById(sondageId);
            sondage.setOuvert(false);
            Sondage modifiedSondage = sondageRepository.save(sondage);
            return new ResponseEntity<>(modifiedSondage, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add dates response entity.
     *
     * @param sondageId the sondage id
     * @param date      the date
     * @return the response entity
     */
    @Operation(summary = "Ajoute des dates possibles à un sondange")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La date a été ajouté au sondage",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sondage.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Sondage non existant",
                    content = @Content)
    })
    @PostMapping("/AddDate/{sondageId}")
    public ResponseEntity<Sondage> addDates(
            @Parameter(description = "Id du sondage concerné")
                @PathVariable("sondageId") Integer sondageId,
            @Parameter(description = "La date à ajouter")
                @RequestBody Date date)    {
        if (sondageRepository.existsById(sondageId)){
            Sondage sondage = sondageRepository.getById(sondageId);
            sondageRepository.deleteById(sondageId);
            sondage.addDate(date);
            Sondage modifiedSondage = sondageRepository.save(sondage);
            return new ResponseEntity<>(modifiedSondage, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete dates response entity.
     *
     * @param sondageId the sondage id
     * @param dateId    the date id
     * @return the response entity
     */
    @Operation(summary = "Supprime une date d'un sondage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La date a été supprimé du sondage",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sondage.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Sondage non existant",
                    content = @Content)
    })
    @DeleteMapping("/DeleteDate/{sondageId}/{dateId}")
    public ResponseEntity<Sondage> deleteDates(
            @Parameter(description = "Id du sondage concerné")
                @PathVariable("sondageId") Integer sondageId,
            @Parameter(description = "Position de la date à supprimer (0, 1, ...)")
                @PathVariable("dateId") int dateId)    {
        if (sondageRepository.existsById(sondageId)){
            Sondage sondage = sondageRepository.getById(sondageId);
            sondageRepository.deleteById(sondageId);
            sondage.deleteDate(dateId);
            Sondage modifiedSondage = sondageRepository.save(sondage);
            return new ResponseEntity<>(modifiedSondage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets dates by id.
     *
     * @param sondageId the sondage id
     * @return the dates by id
     */
    @Operation(summary = "Get toutes les dates possibles d'un sondage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Les dates du sondage ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = Date.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Sondage non existant",
                    content = @Content)
    })
    @GetMapping("/dates/{sondageId}")
    public ResponseEntity<Iterable<Date>> getDatesById(
            @Parameter(description = "Id du sondage concerné")
                @PathVariable("sondageId") Integer sondageId) {
        Optional<Sondage> sondage = sondageRepository.findById(sondageId);
        if (sondage.isPresent()) {
            return new ResponseEntity<>(sondage.get().getDates(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private Sondage createSondageFromForm(SondageForm newSondage){
        return new Sondage(newSondage.getNom(), newSondage.getDescription(), newSondage.getDateLimite(), newSondage.getDates());
    }
}