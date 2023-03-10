package com.systemdesign.zookeeper.monitor;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import lombok.extern.slf4j.Slf4j;

import org.apache.zookeeper.AsyncCallback.StatCallback;

@Slf4j
public class DataMonitor implements StatCallback {
    private ZooKeeper zooKeeper;
    private String zNode;

    public DataMonitor(ZooKeeper zooKeeper, String zNode) {
        this.zooKeeper = zooKeeper;
        this.zooKeeper.exists(zNode, true, this, null); /* This is where watch is being set */
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        /*
         * Since calling of exists on line @17 is asyncCall, hence this is being called
         * when
         * watch has been successfully registered on the server, and the result of exist
         * is ready
         */
    }

    public void processWatchedEvent(WatchedEvent event) {
        this.zooKeeper.getData(zNode, false, null); /*
                                                     * with watch as false, it will pass true if we have defaultWatcher
                                                     */
    }

}
