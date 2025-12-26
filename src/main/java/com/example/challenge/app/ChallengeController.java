package com.example.challenge.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ChallengeController {

    private ChallengeService challengeService;


    public ChallengeController(ChallengeService challengeService){
        this.challengeService=challengeService;
    }

    @GetMapping("/challenges")
    public ResponseEntity<List<Challenge>> getAllChallenges(){
        return new ResponseEntity<>(ChallengeService.getAllChallenges(),HttpStatus.OK);
    }

    @PostMapping("/challenges")
    public  ResponseEntity<String> addChallenge(@RequestBody Challenge challenge){
        boolean isChallengeAdded=
                ChallengeService.addChallenge(challenge);
        if(isChallengeAdded)
            return new ResponseEntity<>("challenge added Successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Challenge not added",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/challenges/{month}")
    public ResponseEntity<Challenge> getAChallenge(@PathVariable String month){
        Challenge challenge=challengeService.getChallenge(month);
        if(challenge!=null)
            return new ResponseEntity<>(challenge,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
 }
