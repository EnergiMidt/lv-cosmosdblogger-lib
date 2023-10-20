package no.em.util.log.cosmos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

/**
 * Created by ivar on 28/06/2018.
 */
public class StringUtils {

    public static String generateRandomId(boolean removeDashes) {
        String uuid = UUID.randomUUID().toString();

        if (removeDashes) {
            uuid = uuid.replace("-", "");
        }

        return uuid;
    }

    public static String generateRandomIntegerId() {
        Random random = new Random();
        long abs = Math.abs(random.nextLong());

        return String.valueOf(abs);
    }

    public static DateFormat getDateFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
    }
}
