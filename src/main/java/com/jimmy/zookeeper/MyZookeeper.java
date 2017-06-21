package com.jimmy.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jimmy on 2017/6/21.
 */
public class MyZookeeper {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.99.100:2181", 6000, watchedEvent -> {
            if (watchedEvent.getState() == Watcher.Event.KeeperState.Expired) {
                System.out.println("过期");
            } else if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                System.out.println("连接");
            }
        });
        List<String> children = zooKeeper.getChildren("/", true);
        children.forEach(child -> System.out.println(child));
        System.out.println("!!!"+new String(zooKeeper.getData("/soa/config/service",true,null)));
    }
}
