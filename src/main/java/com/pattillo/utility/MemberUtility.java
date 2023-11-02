package com.pattillo.utility;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.math.BigDecimal;

public class MemberUtility {
    private static final int MAX_LEVEL = 60;

    public static boolean isValidOwner(String ownerIdRaw, MessageReceivedEvent event) {
        String ownerId;
        boolean isValid = false;

        if (ownerIdRaw.startsWith("<@")) {
            ownerId = getIdFromRaw(ownerIdRaw);
            System.out.println("toon ownerId:    " + ownerId);
            System.out.println("event author id: " + event.getAuthor().getId());

            if (ownerId.equals(event.getAuthor().getId())) {
                isValid = true;
            }
        } else if (ownerIdRaw.equals("Shared")) {
            isValid = true;
        } else {
            ownerId = ownerIdRaw;
            if (ownerId.equals(event.getAuthor().getId())) {
                isValid = true;
            }
        }

        return isValid;
    }

    public static String getIdFromRaw(String rawId) {
        return rawId.substring(2, rawId.length() - 1);
    }

    public static boolean isValidLevel(String level) {
        boolean isValid = false;

        BigDecimal levelBD = new BigDecimal(level);
        double levelDouble = levelBD.doubleValue();

        if (levelBD.scale() <= 2 && levelDouble > 0 && levelDouble <= MAX_LEVEL) {
            isValid = true;
        }

        return isValid;
    }
}
