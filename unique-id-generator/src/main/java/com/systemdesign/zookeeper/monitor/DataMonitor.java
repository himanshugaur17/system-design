package com.systemdesign.zookeeper.monitor;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.AsyncCallback.StatCallback;

public class DataMonitor implements StatCallback{
    private ZooKeeper zooKeeper;
    public ZooKeeper(ZooKeeper zooKeeper, String zNode){
        this.zooKeeper=zooKeeper;
        this.zooKeeper.exists(zNode,true,this,null); /* This is where watch is being set */
    }
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        /*
         * Since setting of watcher is asyncCall, hence this is being called when
         * watch has been successfully registered on the server
         */
    }

}
