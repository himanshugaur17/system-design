package com.systemdesign.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Executor implements Watcher{
    private ZooKeeper zooKeeper;
    public Executor(String host, String zNode) throws IOException{
        this.zooKeeper=new ZooKeeper(host, 3000, this);
    }
    @Override
    public void process(WatchedEvent event) {
        // TODO Auto-generated method stub
        
    }
}
