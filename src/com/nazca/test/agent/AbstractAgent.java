package com.nazca.test.agent;

/*
 * Copyright(c) 2007-2010 by Yingzhi Tech.
 * All Rights Reserved
 */

import com.nazca.test.listener.AgentEventListenerList;
import com.nazca.test.listener.AgentListener;
import com.nazca.io.httprpc.HttpRPCException;
import java.awt.Toolkit;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

/**
 * 抽象的公共Agent
 * @param <T>
 * @author ijay
 */
public abstract class AbstractAgent<T> {
    private AgentEventListenerList listeners = new AgentEventListenerList();
    private SwingWorker<T, Object> worker;

    public final long start() {
        final long seq = System.currentTimeMillis();
        fireStart(seq);

        worker = new SwingWorker<T, Object>() {
            @Override
            protected T doInBackground() throws Exception {
                return doExecute();
            }
            
            @Override
            protected void done() {
                try {
                    T o = get();
                    fireSucceeded(o,seq);
                } catch (InterruptedException ex) {
                    Toolkit.getDefaultToolkit().beep();
                    ex.printStackTrace();
//                    fireFailed("执行线程意外中断", Jk2cMgmtErrorCode.THREAD_INTERRUPTED,seq);
                } catch (Throwable ex) {
                    Toolkit.getDefaultToolkit().beep();
                    ex.printStackTrace();
                    Throwable e = ex;
                    if(ex instanceof ExecutionException){
                        e = ex.getCause();
                    }
                    if (e instanceof HttpRPCException) {
                        HttpRPCException hre = (HttpRPCException) e;
                        fireFailed(hre.getMessage(), hre.getCode(),seq);
                    } else {
//                        fireFailed("未知错误", Jk2cMgmtErrorCode.UNKNOWN_ERROR,seq);
                    }
                }
            }
        };
        
        worker.execute();
        return seq;
    }

    protected abstract T doExecute() throws HttpRPCException;

    public void addListener(AgentListener<T> listener) {
        listeners.add(listener);
    }

    public void removeListener(AgentListener<T> listener){
        listeners.remove(listener);
    }

    public void removeAllListeners(){
        listeners.clear();
    }
    
    public AgentListener<T>[] getListeners(){
        return listeners.getListeners();
    }

    protected void fireStart(long seq) {
        for (AgentListener<T> l : listeners.getListeners()) {
            l.onStarted(seq);
        }
    }

    protected void fireSucceeded(T result,long seq) {
        for (AgentListener<T> l : listeners.getListeners()) {
            l.onSucceeded(result, seq);
        }
    }

    protected void fireFailed(String msg, int errorCode,long seq) {
        for (AgentListener<T> l : listeners.getListeners()) {
            l.onFailed(msg, errorCode, seq);
        }
    }

    public void stop() {
        if (worker != null && !worker.isDone()) {
            worker.cancel(true);
        }
    }
}
