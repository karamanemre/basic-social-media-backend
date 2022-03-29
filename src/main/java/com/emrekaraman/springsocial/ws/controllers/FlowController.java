package com.emrekaraman.springsocial.ws.controllers;

import com.emrekaraman.springsocial.business.abstracts.FlowService;
import com.emrekaraman.springsocial.business.dtos.FlowDto;
import com.emrekaraman.springsocial.core.utilities.DataResult;

import com.emrekaraman.springsocial.dataAccess.abstracts.FlowDao;
import com.emrekaraman.springsocial.entities.concretes.Flow;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/flowcontroller")
public class FlowController {

    private final FlowService flowService;

    public FlowController(FlowService flowService) {
        this.flowService = flowService;
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<Flow>> add(@Valid @RequestBody FlowDto flowDto){
        return ResponseEntity.ok(this.flowService.add(flowDto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<Flow>>> getAll(@RequestParam(required = false,defaultValue = "1")  int pageNo,@RequestParam(required = false,defaultValue = "15") int pageSize){
        return ResponseEntity.ok(this.flowService.getAll(pageNo,pageSize));
    }

    @GetMapping("/getAll/{username}")
    public ResponseEntity<DataResult<List<Flow>>> getAllByUserId(@PathVariable String username,@RequestParam(required = false,defaultValue = "1") int pageNo,@RequestParam(required = false,defaultValue = "15") int pageSize){
        return ResponseEntity.ok(this.flowService.getAllByUserId(username,pageNo,pageSize));
    }
}
