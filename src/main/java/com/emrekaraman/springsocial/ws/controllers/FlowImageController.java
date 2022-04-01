package com.emrekaraman.springsocial.ws.controllers;

import com.emrekaraman.springsocial.business.abstracts.FlowImageService;
import com.emrekaraman.springsocial.business.dtos.FlowImageDto;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/fileController")
public class FlowImageController {

    private final FlowImageService flowImageService;

    public FlowImageController(FlowImageService flowImageService) {
        this.flowImageService = flowImageService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody FlowImageDto flowImageDto){
       return ResponseEntity.ok(this.flowImageService.add(flowImageDto));
    }
}
