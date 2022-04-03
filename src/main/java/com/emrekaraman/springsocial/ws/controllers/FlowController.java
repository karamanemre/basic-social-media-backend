package com.emrekaraman.springsocial.ws.controllers;

import com.emrekaraman.springsocial.auth.userAuthService.UserDetailsManager;
import com.emrekaraman.springsocial.business.abstracts.FlowService;
import com.emrekaraman.springsocial.business.dtos.FlowDto;
import com.emrekaraman.springsocial.core.constraint.abstracts.CurrentUser;
import com.emrekaraman.springsocial.core.utilities.DataResult;

import com.emrekaraman.springsocial.core.utilities.Result;
import com.emrekaraman.springsocial.core.utilities.SuccessDataResult;
import com.emrekaraman.springsocial.dataAccess.abstracts.FlowDao;
import com.emrekaraman.springsocial.entities.concretes.Flow;
import com.emrekaraman.springsocial.entities.concretes.User;
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

    @DeleteMapping("/deleteById")
    public ResponseEntity<Result> deleteById(@RequestParam Long id, @CurrentUser UserDetailsManager userDetailsManager){
        System.out.println(userDetailsManager.getUser().getUsername());
        return ResponseEntity.ok(this.flowService.deleteById(id,userDetailsManager));
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<Flow>>> getAll(@RequestParam(required = false,defaultValue = "1")  int pageNo,@RequestParam(required = false,defaultValue = "15") int pageSize){
        return ResponseEntity.ok(this.flowService.getAll(pageNo,pageSize));
    }

    @GetMapping("/findNewPosts/{id:[0-9]+}")
    public ResponseEntity<DataResult<User>> findNewPosts(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(this.flowService.findNewPosts(id));
    }

    @GetMapping("/findByIdGreaterThan/{id:[0-9]+}")
    public ResponseEntity<DataResult<List<Flow>>> findByIdGreaterThan(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.flowService.findByIdGreaterThan(id));
    }

    @GetMapping("/getAll/{username}")
    public ResponseEntity<DataResult<List<Flow>>> getAllByUserId(@PathVariable String username,@RequestParam(required = false,defaultValue = "1") int pageNo,@RequestParam(required = false,defaultValue = "15") int pageSize){
        return ResponseEntity.ok(this.flowService.getAllByUserId(username,pageNo,pageSize));
    }
}
