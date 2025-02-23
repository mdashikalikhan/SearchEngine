create table search_client(
    SEARCH_KEY varchar2(500),
    CLIENTS_CODE number,
    clients_Name  varchar2(200),
    INDCLIENT_FATHER_NAME varchar2(200),
    IACLINK_ACTUAL_ACNUM varchar2(200),
    CLIENTS_PHN varchar2(50),
    CLIENTS_HOME_BRN_CODE   number,
    CLIENTS_TYPE_FLG    varchar2(50),
    CLIENTS_PAN_GIR_NUM varchar2(200),
    CLIENTS_ADDR1 varchar2(100),
    CLIENTS_ADDR2 varchar2(100),
    CLIENTS_ADDR3 varchar2(100),
    CLIENTS_ADDR4 varchar2(100),
    CLIENTS_ADDR5 varchar2(100),
    primary key (SEARCH_KEY, CLIENTS_CODE)
);