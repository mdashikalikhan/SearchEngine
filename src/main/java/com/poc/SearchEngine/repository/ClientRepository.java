package com.poc.SearchEngine.repository;

import com.poc.SearchEngine.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT CLIENTS_CODE,      CLIENTS_NAME,       INDCLIENT_FATHER_NAME,       IACLINK_ACTUAL_ACNUM,       TO_CHAR (GET_MOBILE_NUM (CLIENTS_CODE))     AS CLIENTS_PHN,       CLIENTS_HOME_BRN_CODE,       CLIENTS_TYPE_FLG,       CLIENTS_PAN_GIR_NUM,       CLIENTS_ADDR1,       CLIENTS_ADDR2,       CLIENTS_ADDR3,       CLIENTS_ADDR4,       CLIENTS_ADDR5  FROM (SELECT CLIENTS_CODE,               CLIENTS_NAME,               INDCLIENT_FATHER_NAME,               IACLINK_ACTUAL_ACNUM,               COALESCE (TO_CHAR (INDCLIENT_TEL_RES),                         TO_CHAR (INDCLIENT_TEL_OFF),                         TO_CHAR (INDCLIENT_TEL_OFF1)),               TO_CHAR (INDCLIENT_TEL_GSM)                   CLIENTS_PHN,               CLIENTS_HOME_BRN_CODE,               CLIENTS_TYPE_FLG,               CLIENTS_PAN_GIR_NUM,               CLIENTS_ADDR1,               CLIENTS_ADDR2,               CLIENTS_ADDR3,               CLIENTS_ADDR4,               CLIENTS_ADDR5,               CLIENTS_ADDR_INV_NUM          FROM CLIENTS,               INDCLIENTS               LEFT JOIN iaclink                   ON     INDCLIENT_CODE = IACLINK_CIF_NUMBER                      AND IACLINK_ENTITY_NUM = 1         WHERE     CLIENTS_CODE = INDCLIENT_CODE               AND CLIENTS_HOME_BRN_CODE = :branchCode               AND CONTAINS(CLIENTS_NAME, :clientNamePattern)>0)",
    nativeQuery = true)
    List<Client> findClientByBranchCodeAndClientName(@Param("branchCode") int branchCode,
                                                     @Param("clientNamePattern") String clientName);
}
