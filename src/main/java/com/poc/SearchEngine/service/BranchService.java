package com.poc.SearchEngine.service;

import com.poc.SearchEngine.entity.Client;
import com.poc.SearchEngine.entity.ProcessedClient;
import com.poc.SearchEngine.entity.SearchTask;
import com.poc.SearchEngine.repository.ClientRepository;
import com.poc.SearchEngine.repository.ProcessedClientRepository;
import com.poc.SearchEngine.repository.SearchTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class BranchService {

    private ClientRepository clientRepository;

    private ProcessedClientRepository processedClientRepository;

    private SearchTaskRepository searchTaskRepository;

    public SearchTask getSearchTask(String searchKey){
        return searchTaskRepository.findById(searchKey).orElse(null);
    }

    @Async("branchTaskExecutor")
    public CompletableFuture<Void> processBranchAsync(Long branchId, String searchKey) {
        // Fetch clients for the branch
        List<Client> clients = clientRepository.findClientByBranchCodeAndClientName(
                branchId.intValue(), searchKey
        );

        // Insert clients into the target table
        for (Client client : clients) {
            ProcessedClient processedClient = new ProcessedClient();
            processedClient.setSearchKey(searchKey);
            processedClient.setClientsCode(client.getClientsCode());
            processedClient.setClientsName(client.getClientsName());
            processedClient.setIndclientFatherName(client.getIndclientFatherName());
            processedClient.setIaclinkActualAcnum(client.getIaclinkActualAcnum());
            processedClient.setClientsPhn(client.getClientsPhn());
            processedClient.setClientsHomeBrnCode(client.getClientsHomeBrnCode());
            processedClient.setClientsTypeFlg(client.getClientsTypeFlg());
            processedClient.setClientsPanGirNum(client.getClientsPanGirNum());
            processedClient.setClientsAddr1(client.getClientsAddr1());
            processedClient.setClientsAddr2(client.getClientsAddr2());
            processedClient.setClientsAddr3(client.getClientsAddr3());
            processedClient.setClientsAddr4(client.getClientsAddr4());
            processedClient.setClientsAddr5(client.getClientsAddr5());
            processedClientRepository.save(processedClient);
        }

        return CompletableFuture.completedFuture(null);
    }
}
