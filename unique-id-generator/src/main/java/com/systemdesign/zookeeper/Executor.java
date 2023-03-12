package com.systemdesign.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.systemdesign.zookeeper.monitor.DataMonitor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Executor implements Watcher {
    private ZooKeeper zooKeeper;
    private DataMonitor dataMonitor;

    public Executor(String host, String zNode) throws IOException {
        log.info("intializing executor class, wrapping zookeeper connection");
        this.zooKeeper = new ZooKeeper(host, 3000, this);
        this.dataMonitor = new DataMonitor(zooKeeper, zNode);
    }

    @Override
    public void process(WatchedEvent event) {
        log.info("executor recieved watch event: {}", event);
        try {
            dataMonitor.processWatchedEvent(event);
        } catch (KeeperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
