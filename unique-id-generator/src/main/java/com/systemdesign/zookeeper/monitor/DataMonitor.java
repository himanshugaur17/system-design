package com.systemdesign.zookeeper.monitor;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import lombok.extern.slf4j.Slf4j;

import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.Watcher.Event.EventType;

@Slf4j
public class DataMonitor implements StatCallback {
    private ZooKeeper zooKeeper;
    private String zNode;

    public DataMonitor(ZooKeeper zooKeeper, String zNode) {
        this.zooKeeper = zooKeeper;
        this.zNode=zNode;
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
        log.info("result of exist for existNode is ready for path {}", path);
        boolean exists = false;
        Code rcCode = Code.get(rc);
        switch (rcCode) {
            case OK:
                exists = true;
                break;
            case NONODE:
                exists = false;
                break;
            case NOAUTH:
            case SESSIONEXPIRED:
                log.info("session expired or no auth");
                return;
            default:
                zooKeeper.exists(path, true, this, null);
        }
        byte[] dataOfZNode = null;
        getNodeData(path, exists, dataOfZNode);
    }

    private void getNodeData(String path, boolean exists, byte[] dataOfZNode) {
        if (exists) {
            try {
                dataOfZNode = zooKeeper.getData(path, false, null);
            } catch (KeeperException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String zNodeData = new String(dataOfZNode);
        log.info("data from zNode: {}", zNodeData);
    }

    public void processWatchedEvent(WatchedEvent event) throws KeeperException, InterruptedException {
        String path = event.getPath();
        if (event.getType() == EventType.None) {
            /*
             * This means only the connection state has changed, no changes in data zNode.
             */
            switch (event.getState()) {
                case SyncConnected:
                    /*
                     * The client connection from one server of the ensemble
                     * has been transferred to another
                     */
                    log.info("connection transferred");
                    break;
                case Expired:
                    /*
                     * The zookeeper cluster has expired the session and now we have to
                     * create a new connection by creating a new Zookeeper object specifying
                     * the host url
                     */
                    log.info("connection expired");
                    break;
                default:
                    log.info("in default case of event.getStat()");
                    break;
            }
        } else {
            /* This means something has changed */
            log.info("notified for change on zNode path: {}", path);
            /* Let's get the data for what has changed */
            String nodeData = new String(this.zooKeeper.getData(zNode, true, null)); 
            log.info("data from zNode: {}", nodeData);
        }
    }

}
