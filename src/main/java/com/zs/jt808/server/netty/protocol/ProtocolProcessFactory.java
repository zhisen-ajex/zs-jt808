package com.zs.jt808.server.netty.protocol;

import com.zs.jt808.server.constants.Jt808MessageType;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;


@Component
@Slf4j
@AllArgsConstructor
public class ProtocolProcessFactory {

    private final ProtocolProcess authenticationProcess;
    private final ProtocolProcess dataTransmissionProcess;
    private final ProtocolProcess linkHeartbeatProcess;
    private final ProtocolProcess locationInfoBatchProcess;
    private final ProtocolProcess locationInfoProcess;
    private final ProtocolProcess logoutProcess;
    private final ProtocolProcess registerProcess;
    private final ProtocolProcess requestTimeProcess;

    private final ProtocolProcess responseCommonProcess;
    private final ProtocolProcess responseRegisterProcess;
    private final ProtocolProcess responseTimeProcess;

    private final Map<Jt808MessageType, ProtocolProcess> processMap = new EnumMap<>(Jt808MessageType.class);

    @PostConstruct
    public void init() {
        processMap.put(Jt808MessageType.LINK_HEARTBEAT, linkHeartbeatProcess);
        processMap.put(Jt808MessageType.REGISTER_UP, registerProcess);
        processMap.put(Jt808MessageType.LOGOUT, logoutProcess);
        processMap.put(Jt808MessageType.AUTHENTICATION, authenticationProcess);
        processMap.put(Jt808MessageType.LOCATION_INFO_UP, locationInfoProcess);
        processMap.put(Jt808MessageType.LOCATION_INFO_UP_BATCH, locationInfoBatchProcess);
        processMap.put(Jt808MessageType.REQUEST_TIME, requestTimeProcess);
        processMap.put(Jt808MessageType.DATA_TRANSMISSION_UP, dataTransmissionProcess);
        processMap.put(Jt808MessageType.DATA_TRANSMISSION_DOWN, responseTimeProcess);
        processMap.put(Jt808MessageType.REQUEST_TIME_DOWN, responseTimeProcess);
        processMap.put(Jt808MessageType.RESPONSE_COMMON_DOWN, responseCommonProcess);
        processMap.put(Jt808MessageType.REGISTER_DOWN, responseRegisterProcess);
    }

    public ProtocolProcess getInstance(Jt808MessageType type) {
        ProtocolProcess process = processMap.get(type);
        if (process == null) {
            log.error("No process found for type: {}", type);
        }
        return process;
    }
    //private ProtocolProcessProperties protocolProcessProperties;

/*    public ProtocolProcess getInstance(Jt808MessageType type){
        try {
            ProtocolProcess bean = (ProtocolProcess) Class.forName(protocolProcessProperties.getProcess().get(type)).getDeclaredConstructor().newInstance();
            Application.applicationContext.getAutowireCapableBeanFactory().autowireBean(bean);
            return bean;
        } catch (NullPointerException e){
            return null;
        } catch (Exception e) {
            log.error("getInstance error:{} ",e.toString(), e);
        }
        return null;
    }*/

}
