package com.bnm.controller;

import com.bnm.entity.Sondage;
import com.bnm.entity.User;
import com.bnm.entity.Vote;
import com.bnm.entity.VoteEnum;
import com.bnm.repository.SondageRepository;
import com.bnm.repository.UserRepository;
import com.bnm.repository.VoteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.bnm.entity.VoteEnum.DISPONIBLE;
import static com.bnm.entity.VoteEnum.PEUTETRE;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/vote")
public class VoteController {

    private final UserRepository userRepository;
    private final SondageRepository sondageRepository;
    private final VoteRepository voteRepository;

    /**
     * Instantiates a new User controller.
     *  @param userRepository the user repository
     * @param sondageRepository the sondage repository
     * @param voteRepository the vote repository
     */
    public VoteController(UserRepository userRepository, SondageRepository sondageRepository, VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.sondageRepository = sondageRepository;
        this.voteRepository = voteRepository;
    }


    /**
     * Vote response entity.
     *
     * @param idUser    the id user
     * @param idSondage the id sondage
     * @param vote      the vote
     * @return the response entity
     */
    @Operation(summary = "Permet à un user de voter pour une date à un sondage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Le vote a été comptabilisé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vote.class)) }),
            @ApiResponse(responseCode = "400", description = "Le sondage est fini",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Sondage ou User non existant",
                    content = @Content)
    })
    @PostMapping("/{idUser}/{idSondage}/{vote}/{date}")
    public ResponseEntity<Vote> vote(@Parameter(description = "L'id du user qui va voter")
                                         @PathVariable("idUser") Integer idUser,
                                     @Parameter(description = "L'id du sondage concerné")
                                        @PathVariable("idSondage") Integer idSondage,
                                     @Parameter(description = "Vote qui va être comptabilisé")
                                        @PathVariable("vote") VoteEnum vote,
                                     @Parameter(description = "Date pour laquelle elle est voté")
                                        @PathVariable("date") Date date) {
        Optional<User> user = userRepository.findById(idUser);
        Optional<Sondage> sondage = sondageRepository.findById(idSondage);
        Date now = new Date(System.currentTimeMillis());
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else if (sondage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else if (!sondage.get().getDates().contains(date)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else if(!sondage.get().isOuvert() || sondage.get().getDateLimite().after(now)){
            Sondage modifiedSondage = sondage.get();
            modifiedSondage.setOuvert(false);
            sondageRepository.deleteById(idSondage);
            sondageRepository.save(modifiedSondage);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Vote newVote = new Vote(sondage.get(), user.get(), date , vote);
        Vote addVote = voteRepository.save(newVote);
        return new ResponseEntity<>(addVote, HttpStatus.CREATED);
    }

    /**
     * Gets all vote for sondage.
     *
     * @param idSondage the id sondage
     * @return the all vote for sondage
     */
    @Operation(summary = "Get tout les votes d'un sondage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tout les votes du sondage ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = Vote.class
                            ))}) })
    @GetMapping("/sondage/{sondageId}")
    public ResponseEntity<Iterable<Vote>> getAllVoteForSondage(
            @Parameter(description = "L'id du sondage concerné")
                @PathVariable("sondageId") Integer idSondage) {
        return new ResponseEntity<>(voteRepository.findBySondage(idSondage), HttpStatus.OK);
    }

    /**
     * Gets meilleur date disponible.
     *
     * @param sondageId the sondage id
     * @return the meilleur date disponible
     */
    @Operation(summary = "Get la meilleur date d'un sondage en se basant uniquement sur les 'disponible'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La date a été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Date.class
                            ))}) })
    @GetMapping("/meilleurDateDisponible/{sondageId}")
    public ResponseEntity<Date> getMeilleurDateDisponible(
            @Parameter(description = "L'id du sondage concerné")
                @PathVariable("sondageId") Integer sondageId) {
        ArrayList<VoteEnum> choix = new ArrayList<>();
        choix.add(DISPONIBLE);
        return new ResponseEntity<>(getBestDate(sondageId, choix), HttpStatus.OK);
    }

    /**
     * Gets meilleur date peut etre.
     *
     * @param sondageId the sondage id
     * @return the meilleur date peut etre
     */
    @Operation(summary = "Get la meilleur date d'un sondage en prenant en compte les 'peut-être'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La date a été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Date.class
                            ))}) })
    @GetMapping("/meilleurPeutEtre/{sondageId}")
    public ResponseEntity<Date> getMeilleurDatePeutEtre(
            @Parameter(description = "L'id du sondage concerné")
                @PathVariable("sondageId") Integer sondageId) {
        ArrayList<VoteEnum> choix = new ArrayList<>();
        choix.add(DISPONIBLE);
        choix.add(PEUTETRE);
        return new ResponseEntity<>(getBestDate(sondageId, choix), HttpStatus.OK);
    }

    private Date getBestDate(Integer idSondage, ArrayList<VoteEnum> voteEnums){
        List<Vote> votes = voteRepository.findBySondage(idSondage);
        HashMap<Date, Integer> dateMap = new HashMap<>();

        for (Vote vote :votes) {
            Date date = vote.getDateChoisie();
            if(voteEnums.contains(vote.getChoix())){
                if (dateMap.get(date) != null){
                    dateMap.put(date, 1);
                } else {
                    dateMap.replace(date, dateMap.get(date) + 1);
                }
            }
        }

        Date meilleurDate = new Date();
        Integer value = -1;
        for ( Map.Entry<Date, Integer> dateIntegerEntry : dateMap.entrySet()) {
            if (dateIntegerEntry.getValue() > value) {
                meilleurDate = dateIntegerEntry.getKey();
                value = dateIntegerEntry.getValue();
            }
        }

        return meilleurDate;
    }
}
