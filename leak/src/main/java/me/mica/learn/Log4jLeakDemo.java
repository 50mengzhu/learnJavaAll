package me.mica.learn;

import org.apache.log4j.Logger;
import org.apache.log4j.net.SimpleSocketServer;
import org.apache.log4j.spi.LoggerFactory;

public class Log4jLeakDemo {

    private static final Logger log = Logger.getLogger(Log4jLeakDemo.class);

    public static void main(String[] args) {
        System.out.println("start ... ");

        String[] arg = {"4444", "src/log4j.properties"};
        SimpleSocketServer.main(arg);

        log.info("end...");
    }

}
