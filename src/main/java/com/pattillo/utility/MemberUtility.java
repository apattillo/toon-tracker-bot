package com.pattillo.utility;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public static boolean isValidRace(String race) {
        boolean isValid;

        switch (race.toUpperCase()) {
            case "BAR":
            case "DEF":
            case "DWF":
            case "ERU":
            case "GNM":
            case "HEF":
            case "HFL":
            case "HIE":
            case "HUM":
            case "IKS":
            case "OGR":
            case "TRL":
            case "ELF":
                isValid = true;
                break;
            default:
                isValid = false;
                break;
        }
        return isValid;
    }

    public static boolean isValidClass(String toonClass) {
        boolean isValid;

        switch (toonClass.toLowerCase()) {
            case "bard":
            case "cleric":
            case "druid":
            case "enchanter":
            case "magician":
            case "monk":
            case "necromancer":
            case "paladin":
            case "ranger":
            case "rogue":
            case "shadowknight":
            case "shaman":
            case "warrior":
            case "wizard":
                isValid = true;
                break;
            default:
                isValid = false;
                break;
        }
        return isValid;
    }

    public static double round(double value, int places) {
        BigDecimal bd = BigDecimal.valueOf(value);
        BigDecimal rounded = bd.setScale(places, RoundingMode.HALF_UP);
        return rounded.doubleValue();
    }
}
