package edu.umb.cs.cs681.hw13;

import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.*;
import java.io.File;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

class AccessCounter{

    HashMap<Path,Integer> accessCounter = new HashMap<>();
    private static AccessCounter instance = null;

    private AccessCounter(){};
    private static ReentrantLock lock = new ReentrantLock();
    private  ReentrantReadWriteLock accessLock = new ReentrantReadWriteLock();

    public static AccessCounter getInstance(){

        lock.lock();
        try{
            if(instance==null) {
                instance = new AccessCounter();
                System.out.println("Creating a new singleton");
            } else {
                System.out.println("Returning already created singleton");
            }
            return instance;

        }finally {
            lock.unlock();
        }
    }

    public void increment(Path path) {
        accessLock.writeLock().lock();
        try {
            System.out.println("Incrementing file : " + path);
            if (accessCounter.containsKey(path)) {
                accessCounter.put(path, accessCounter.get(path) + 1);
            } else {
                accessCounter.put(path, 1);
            }
        } finally {
            accessLock.writeLock().unlock();
        }
    }

    public int getCount(Path path) {
        accessLock.readLock().lock();
        try {
            if (accessCounter.containsKey(path)) {
                return accessCounter.get(path);
            } else {
                return 0;
            }
        } finally {
            accessLock.readLock().unlock();
        }
    }
}

public class RequestHandler implements Runnable {


    private final ReentrantLock requestLock = new ReentrantLock();
    boolean done = false;
   public void setDone(){
        requestLock.lock();
        try {
            done = true;
        } finally {
            requestLock.unlock();
        }

    }
    public void run() {

        while (true) {

            requestLock.lock();
            try {
                if (done) {
                    System.out.println("Stopping thread.");
                    break;
                }
            }finally {
                requestLock.unlock();
            }

            File folder = new File("./files/");
            File[] listOfFiles = folder.listFiles();
            int rnd = new Random().nextInt(listOfFiles.length);

            Path filePath = listOfFiles[rnd].toPath();
            AccessCounter.getInstance().increment(filePath);
            int count = AccessCounter.getInstance().getCount(listOfFiles[rnd].toPath());

            System.out.println("File : " + filePath + " Count : " + count);

            try{
                Thread.sleep(5);
            }catch(InterruptedException ex){
                continue;
            }
        }

    }


    public static void main(String[] args) throws InterruptedException{

        ArrayList<Thread> threads = new ArrayList<>();
        RequestHandler req = new RequestHandler();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread( req);
            threads.add(thread);
        }

        for (Thread thread: threads
             ) {
            thread.start();
        }

        Thread.sleep(10);

        for (Thread thread: threads
             ) {
            thread.interrupt();
        }
        req.setDone();

        try {

            for (Thread thread: threads) {
                thread.join();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}



