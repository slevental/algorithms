package org.eslion.zookeeper;

import com.sun.org.apache.xpath.internal.functions.FuncNumber;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DistributedLock {
    private static final Random RANDOM = new Random();
    private final ZooKeeper zooKeeper;
    private String lock;

    public DistributedLock(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        File sharedStorage = new File("/tmp/shared");
        if (!sharedStorage.exists() && !sharedStorage.createNewFile()) {
            throw new IllegalStateException("cannot create " + sharedStorage.getAbsolutePath());
        }

        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", Integer.MAX_VALUE, null);
        DistributedLock lock = new DistributedLock(zooKeeper);
        for (; ; ) {
            lock.lock();
            try {
                int counter = readCounter(sharedStorage);
                compareAndSet(sharedStorage, counter, counter + 1);
            } finally {
                lock.unlock();
            }
            Thread.sleep(RANDOM.nextInt(100));
        }
    }

    private static void compareAndSet(File sharedStorage, int oldValue, int newValue) throws IOException {
        assert readCounter(sharedStorage) == oldValue;
        IOUtils.write(String.valueOf(newValue), new FileOutputStream(sharedStorage));
    }

    private static int readCounter(File sharedStorage) throws IOException {
        String s = FileUtils.readFileToString(sharedStorage);
        return StringUtils.isBlank(s) ? 0 : Integer.parseInt(s);
    }

    public synchronized void lock() throws InterruptedException, KeeperException {
        lock = zooKeeper.create("/lock/id-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        for (; ; ) {
            List<String> lock = zooKeeper.getChildren("/lock", false);
            Collections.sort(lock);
            int lockIndex = lock.indexOf(FilenameUtils.getBaseName(this.lock));
            if (lockIndex == 0) {
                System.out.println("Acquire a lock: " + this.lock);
                return;
            }
            zooKeeper.exists("/lock" + lock.get(lockIndex - 1), true);
        }
    }

    public synchronized void unlock() throws InterruptedException, KeeperException {
        zooKeeper.delete(lock, 0);
        System.out.println("Drop the lock: " + lock);
    }
}
