package com.poc.SearchEngine.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Client {


    @Id
    @Column(name = "CLIENTS_CODE")
    private Long clientsCode;



    @Column(name = "CLIENTS_NAME")
    private String clientsName;

    @Column(name = "INDCLIENT_FATHER_NAME")
    private String indclientFatherName;

    @Column(name = "IACLINK_ACTUAL_ACNUM")
    private String iaclinkActualAcnum;

    @Column(name = "CLIENTS_PHN")
    private String clientsPhn;

    @Column(name = "CLIENTS_HOME_BRN_CODE")
    private int clientsHomeBrnCode;

    @Column(name = "CLIENTS_TYPE_FLG")
    private String clientsTypeFlg;

    @Column(name = "CLIENTS_PAN_GIR_NUM")
    private String clientsPanGirNum;

    @Column(name = "CLIENTS_ADDR1")
    private String clientsAddr1;

    @Column(name = "CLIENTS_ADDR2")
    private String clientsAddr2;

    @Column(name = "CLIENTS_ADDR3")
    private String clientsAddr3;

    @Column(name = "CLIENTS_ADDR4")
    private String clientsAddr4;

    @Column(name = "CLIENTS_ADDR5")
    private String clientsAddr5;

}
