package Lesson_6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainLog {

    public static void main(String[] args) {

        Logger logger = LogManager.getLogger("PropertiesConfig");
        logger.info("qwe");

    }

}
