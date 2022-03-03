package com.bnm.controller;

import com.bnm.entity.Commentaire;
import com.bnm.entity.Sondage;
import com.bnm.repository.CommentaireRepository;
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

import java.util.Optional;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/commentaire")
public class CommentaireController {

    private final CommentaireRepository comRepository;
    private  final SondageRepository sondageRepository;

    /**
     * Instantiates a new Commentaire controller.
     *
     * @param comRepository     the com repository
     * @param sondageRepository the sondage repository
     */
    public CommentaireController(CommentaireRepository comRepository, SondageRepository sondageRepository) {
        this.comRepository = comRepository;
        this.sondageRepository = sondageRepository;
    }

    /**
     * Gets by id.
     *
     * @param idSondage the id sondage
     * @return the by nom
     */
    @Operation(summary = "Get tout les commentaires d'un sondange en fonction de son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Les commentaires du sondage ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = Commentaire.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Sondage non existant",
                    content = @Content)
    })
    @GetMapping("/{idSondage}")
    public ResponseEntity<Iterable<Commentaire>> getById(
            @Parameter(description = "Id du sondage concerné")
                @PathVariable("idSondage") Integer idSondage) {
        return new ResponseEntity<>(comRepository.findBySondage(idSondage), HttpStatus.OK);
    }


    /**
     * Create commendaire response entity.
     *
     * @param idSondage   the id sondage
     * @param commentaire the commentaire
     * @return the response entity
     */
    @Operation(summary = "Ajoute un commentaire à un sondage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "le commentaire a été ajouté",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Commentaire.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Sondage non existant",
                    content = @Content)
    })
    @PostMapping("/{idSondage}")
    public ResponseEntity<Commentaire> createCommentaire(
            @Parameter(description = "Id du sondage concerné")
                @PathVariable("idSondage") Integer idSondage,
            @Parameter(description = "Le commentaire à ajouter")
                @RequestBody String commentaire) {
        Optional<Sondage> sondage = sondageRepository.findById(idSondage);
        if (sondage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Commentaire newCom = new Commentaire(commentaire, sondage.get());
        Commentaire addedCom = comRepository.save(newCom);
        return new ResponseEntity<>(addedCom, HttpStatus.CREATED);
    }

    /**
     * Update sondage response entity.
     *
     * @param idComm      the id comm
     * @param commentaire the commentaire
     * @return the response entity
     */
    @Operation(summary = "Modifie un commentaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "le commentaire a été modifié",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Commentaire.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Commentaire non existant",
                    content = @Content)
    })
    @PutMapping("/{idCommentaire}")
    public ResponseEntity<Commentaire> updateSondage(
            @Parameter(description = "Id du commentaire concerné")
                @PathVariable("idCommentaire") Integer idComm,
            @Parameter(description = "Comment le commentaire doit être modifié")
                @RequestBody String commentaire)    {
        if (comRepository.existsById(idComm)){
            Commentaire oldComm = comRepository.getById(idComm);
            comRepository.deleteById(idComm);
            oldComm.setTexte(commentaire);
            Commentaire newComm = comRepository.save(oldComm);
            return new ResponseEntity<>(newComm, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete commentaire response entity.
     *
     * @param idComm the id comm
     * @return the response entity
     */
    @Operation(summary = "Supprime un commentaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "le commentaire a été supprimé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class
                            ))}),
            @ApiResponse(responseCode = "404", description = "Commentaire non existant",
                    content = @Content)
    })
    @DeleteMapping("/{idCommentaire}")
    public ResponseEntity<Integer> deleteCommentaire(
            @Parameter(description = "Id du commentaire concerné")
            @PathVariable("idCommentaire") Integer idComm) {
        if (comRepository.existsById(idComm)){
            comRepository.deleteById(idComm);
            return new ResponseEntity<>(idComm, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
