package com.emrekaraman.springsocial.ws.controllers;


import com.emrekaraman.springsocial.business.abstracts.FollowedListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/followedListController")
public class FollowedListController {

    private final FollowedListService followedListService;

    public FollowedListController(FollowedListService followedListService) {
        this.followedListService = followedListService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> findByUserId(@RequestParam Long userId){
        return ResponseEntity.ok(followedListService.findByUserId(userId));
    }
}
