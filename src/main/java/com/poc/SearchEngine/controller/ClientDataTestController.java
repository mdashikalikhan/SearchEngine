package com.poc.SearchEngine.controller;


import com.poc.SearchEngine.entity.Client;
import com.poc.SearchEngine.model.SearchModel;
import com.poc.SearchEngine.service.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class ClientDataTestController {
    private ClientService clientService;

    @PostMapping("/search") public ResponseEntity<Object> getCLientData(@Valid@RequestBody SearchModel searchModel){
        List<Client> clients = clientService.getClients(26, searchModel.getSearchKey());
        /*if(clients!=null){
            return ResponseEntity.ok(clients);
        }*/
        return ResponseEntity.ofNullable(clients);

    }


}
