package de.dal3x.dalbot.util;

import de.dal3x.dalbot.external.TenorScraper;

public class TenorHelper {
    
    public static String addLinkToMessage(String message, String searchterm, int limit) {
        return message + "\n" + TenorScraper.getRandomImageURL(searchterm, limit).toString();
    }

}
