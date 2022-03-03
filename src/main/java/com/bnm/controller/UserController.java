package com.bnm.controller;

import com.bnm.entity.User;
import com.bnm.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    /**
     * Instantiates a new User controller.
     *
     * @param userRepository the user repository
     */
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Home iterable.
     *
     * @return the iterable
     */
    @Operation(summary = "Get tout les user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tout les users ont été trouvé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = User.class
                    ))}) })
    @GetMapping("")
    public ResponseEntity<Iterable<User>> home() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }


    /**
     * Create sondage response entity.
     *
     * @param username the new user
     * @return the response entity
     */
    @Operation(summary = "Créer un nouvel user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Le user a été créé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) })})
    @PostMapping("")
    public ResponseEntity<User> createSondage(@Parameter(description = "Le nom d'utilisateur du nouvel user")
                                                  @RequestBody String username) {
        User createUser = new User(username);
        User user = userRepository.save(createUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
