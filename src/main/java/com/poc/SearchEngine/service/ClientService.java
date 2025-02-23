package com.poc.SearchEngine.service;

import com.poc.SearchEngine.entity.Client;
import com.poc.SearchEngine.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClientService {
    private ClientRepository clientRepository;

    public List<Client> getClients(int branchCode, String searchKey){
       return clientRepository.findClientByBranchCodeAndClientName(branchCode, searchKey);

    }
}
