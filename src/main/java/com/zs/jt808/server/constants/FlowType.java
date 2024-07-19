package com.zs.jt808.server.constants;


public enum FlowType {

    INITIATIVE("initiative_flow")    //主动

    , PASSIVE("passive_flow");  //被动

    private String value;

    FlowType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
