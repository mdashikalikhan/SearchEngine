package com.poc.SearchEngine.service;

import com.poc.SearchEngine.entity.Client;
import com.poc.SearchEngine.entity.ProcessedClient;
import com.poc.SearchEngine.entity.SearchTask;
import com.poc.SearchEngine.model.SearchInfoModel;
import com.poc.SearchEngine.repository.ClientRepository;
import com.poc.SearchEngine.repository.ProcessedClientRepository;
import com.poc.SearchEngine.repository.SearchTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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

    public void deleteSearch(String searchKey){
        processedClientRepository.deleteAllBySearchKey(searchKey);
        searchTaskRepository.deleteBySearchKey(searchKey);
    }
    public SearchInfoModel getSearchInfo(String searchKey){
        SearchTask searchTask = getSearchTask(searchKey);
        if(searchTask==null){
            return null;
        }

        SearchInfoModel searchInfoModel = new SearchInfoModel();

        searchInfoModel.setSearchKey(searchKey);
        searchInfoModel.setStartTime(searchTask.getStartTime()==null? null :searchTask.getStartTime());
        searchInfoModel.setEndTime(searchTask.getEndTime()==null? null :searchTask.getEndTime());

        searchInfoModel.setRecords(processedClientRepository.getTotalRecords(searchKey));
        return searchInfoModel;


    }

    @Async("branchTaskExecutor")
    public CompletableFuture<Void> processBranchAsync(Long branchId, String searchKey) {
        // Fetch clients for the branch
        List<Client> clients = clientRepository.findClientByBranchCodeAndClientName(
                branchId.intValue(), searchKey
        );

        // Insert clients into the target table
        List<ProcessedClient> processedClients = new ArrayList<>();
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
            //processedClients.add(processedClient);
        }

        //Batch Insert
        //processedClientRepository.saveAll(processedClients);

        return CompletableFuture.completedFuture(null);
    }

    @Async("branchTaskExecutor")
    public CompletableFuture<Void> processBranchSingleCharacterAsync(Long branchId, String searchKey) {
        // Fetch clients for the branch
        List<Client> clients = clientRepository.findClientByBranchCodeAndSingleCharacter(
                branchId.intValue(), searchKey
        );

        // Insert clients into the target table
        List<ProcessedClient> processedClients = new ArrayList<>();
        clients.stream().filter(c->c.getClientsName().toUpperCase().contains(searchKey)).forEach(
                client -> {
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
        );
        /*for (Client client : clients) {

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
            //processedClients.add(processedClient);
        }*/

        //Batch Insert
        //processedClientRepository.saveAll(processedClients);

        return CompletableFuture.completedFuture(null);
    }

    @Async("branchTaskExecutor")
    public CompletableFuture<Void> processBranchPhoneticAsync(Long branchId, String searchKey) {
        // Fetch clients for the branch
        List<Client> clients = clientRepository.findClientByBranchCodeAndPhonetic(
                branchId.intValue(), searchKey
        );

        // Insert clients into the target table
        List<ProcessedClient> processedClients = new ArrayList<>();
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
            //processedClients.add(processedClient);
        }

        //Batch Insert
        //processedClientRepository.saveAll(processedClients);

        return CompletableFuture.completedFuture(null);
    }
}
