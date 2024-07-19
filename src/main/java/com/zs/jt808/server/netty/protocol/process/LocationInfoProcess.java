package com.zs.jt808.server.netty.protocol.process;

import com.zs.jt808.server.constants.Jt808Constants;
import com.zs.jt808.server.netty.protocol.AbstractProtocolProcess;
import com.zs.jt808.server.netty.request.Jt808Message;
import com.zs.jt808.server.entity.LocationInfo;
import com.zs.jt808.server.service.KafkaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 【0x0200】位置信息汇报
 **/
@Slf4j
@Component
public class LocationInfoProcess extends AbstractProtocolProcess {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private KafkaService kafkaService;

    protected Jt808Message resolve(Jt808Message message) {
        LocationInfo msg = new LocationInfo(message);
        return LocationInfoBatchProcess.getLocationInfo(message.getMsgBodyBytes(), msg);
    }

    @Override
    protected Jt808Message process(Jt808Message message) {
        LocationInfo msg = (LocationInfo) message;
        if (msg.getReplyCode() != Jt808Constants.RESP_SUCCESS) {
            return msg;
        }
//        if (!checkLongLat(msg)) {
//            log.warn(">>> 经纬度范围错误>>>terminalPhone:{},位置信息:{}", msg.getHeader().getTerminalPhone(), msg);
//            msg.setReplyCode(Jt808Constants.RESP_FAILURE);
//            return msg;
//        }
        String plate = stringRedisTemplate.opsForValue().get("vehicle:" + msg.getHeader().getTerminalPhone());
        log.debug("车辆:{} 的位置信息:{}",plate,msg);
        kafkaService.send("obd_location", msg);
        log.debug("位置信息:{}", msg);
        return msg;
    }

    /**
     * 经纬度过滤
     *
     * @param msg
     * @return
     */
    public static boolean checkLongLat(LocationInfo msg) {
        //经度最大是180° 最小是0°
        double longitude = msg.getLongitude();
        if (0.0 > longitude || 180.0 < longitude) {
            return false;
        }
        //纬度最大是90° 最小是0°
        double latitude = msg.getLatitude();
        if (0.0 > latitude || 90.0 < latitude) {
            return false;
        }
        if (0.0 == latitude && 0.0 == longitude) {
            return false;
        }
        return true;
    }
}
