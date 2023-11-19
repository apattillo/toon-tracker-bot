package com.pattillo.model;

import com.pattillo.entity.ToonInfo;

public class ToonExperience {
    private static final int CLASS_XP_MODIFIER = 100;
    private ToonInfo toonInfo;
    private Double totalExperience;
    private Double experienceSharePct;

    public ToonExperience(ToonInfo toonInfo) {
        this.toonInfo = toonInfo;
        this.totalExperience = calculateTotalExperience(toonInfo);
    }

    public ToonInfo getToonInfo() {
        return toonInfo;
    }

    public void setToonInfo(ToonInfo toonInfo) {
        this.toonInfo = toonInfo;
    }

    public Double getTotalExperience() {
        return totalExperience;
    }

    public Double getExperienceSharePct() {
        return experienceSharePct;
    }

    public void setExperienceSharePct(Double experienceSharePct) {
        System.out.println("Setting experienceSharePct for " + this.getToonInfo().getToonName() + " to " + experienceSharePct + "%");
        this.experienceSharePct = experienceSharePct;
    }

    private Double calculateTotalExperience(ToonInfo toonInfo) {
        int previousLevel = Integer.parseInt(toonInfo.getToonLevel()) - 1;
        double raceMultiplier = getRaceMultipler(toonInfo.getToonRace());
        System.out.println("Using " + raceMultiplier + " for " + toonInfo.getToonName() + "'s raceMultiplier");
        double hellMultiplier = getHellModifier(previousLevel);
        Double returnVal = Math.pow(previousLevel, 3) * raceMultiplier * CLASS_XP_MODIFIER * hellMultiplier;
        System.out.println(toonInfo.getToonName() + ": " + previousLevel + "^3 * " + raceMultiplier + " * " + CLASS_XP_MODIFIER + " * " + hellMultiplier);
        return returnVal;
    }

    private double getRaceMultipler(String race) {
        double multipler;

        switch (race.toUpperCase()) {
            case "BAR":
                multipler = 10.5d;
                break;
            case "HFL":
                multipler = 9.5d;
                break;
            case "IKS":
            case "TRL":
                multipler = 12d;
                break;
            case "OGR":
                multipler = 11.5d;
                break;
            default:
                multipler = 10d;
                break;
        }
        return multipler;
    }

    private double getHellModifier(int level) {
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
