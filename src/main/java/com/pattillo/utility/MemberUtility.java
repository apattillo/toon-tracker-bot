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
            default:
                isValid = false;
        }
        return isValid;
    }

    public static boolean isValidClass(String toonClass) {
        boolean isValid = false;

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
            default:
                isValid = false;
        }
        return isValid;
    }

    public static double getRaceMultipler(String race) {
        double multipler;

        switch (race.toUpperCase()) {
            case "BAR":
                multipler = 10.5d;
            case "HFL":
                multipler = 9.5d;
            case "IKS":
            case "TRL":
                multipler = 12d;
            case "OGR":
                multipler = 11.5d;
            default:
                multipler = 10d;
        }
        return multipler;
    }

    public static double getHellModifier(int level) {
        double hellModifier;
        if (level >= 30 && level < 35) {
            hellModifier = 1.1d;
        } else if (level >= 35 && level < 40) {
            hellModifier = 1.2d;
        } else if (level >= 40 && level < 45) {
            hellModifier = 1.3d;
        } else if (level >= 45 && level < 51) {
            hellModifier = 1.4d;
        } else if (level == 51) {
            hellModifier = 1.5d;
        } else if (level == 52) {
            hellModifier = 1.6d;
        } else if (level == 53) {
            hellModifier = 1.7d;
        } else if (level == 54) {
            hellModifier = 1.9d;
        } else if (level == 55) {
            hellModifier = 2d;
        } else if (level == 56) {
            hellModifier = 2.1d;
        } else if (level == 57) {
            hellModifier = 2.2d;
        } else if (level == 58) {
            hellModifier = 2.3d;
        } else if (level == 59) {
            hellModifier = 2.5d;
        } else if (level == 60) {
            hellModifier = 2.6d;
        } else {
            hellModifier = 1d;
        }
        return hellModifier;
    }
}
