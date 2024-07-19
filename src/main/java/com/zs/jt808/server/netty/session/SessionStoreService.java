package com.zs.jt808.server.netty.session;

/**
 * 会话存储接口类
 */
public interface SessionStoreService {

    /**
     * 存储会话
     */
    void put(String clientId, SessionStore sessionStore);

    /**
     * 获取会话
     */
    SessionStore get(String clientId);

    /**
     * clientId的会话是否存在
     */
    boolean containsKey(String clientId);

    /**
     * 删除会话
     */
    void remove(String clientId);

    void connectClean(String id);

    void connectRemove(String id, String clientId);

}
