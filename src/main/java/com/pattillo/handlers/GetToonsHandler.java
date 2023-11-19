package com.pattillo.handlers;

import com.pattillo.entity.ToonInfo;
import com.pattillo.model.ToonExperience;
import com.pattillo.repository.ToonInfoRepo;
import com.pattillo.utility.MemberUtility;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetToonsHandler implements CommandHandler {

    private static final int MAX_LEVEL = 60;
    private static final int LEVEL_RANGE_FOR_MAINS = 5;

    private final ToonInfoRepo toonInfoRepo;

    public GetToonsHandler(ToonInfoRepo toonInfoRepo) {
        this.toonInfoRepo = toonInfoRepo;
    }

    @Override
    public String handle(String commandString, MessageReceivedEvent event) {
        List<ToonInfo> toons = toonInfoRepo.getToons();
        if (toons.isEmpty()) {
            return "No toons found";
        }

        return formatToonOutput(toons);
    }

    private String formatToonOutput(List<ToonInfo> toons) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Current high: **%s**\n\n", getHighLevel(toons)));
        sb.append(String.format("XP share at current levels: \n"));
        calculateAndDisplayXpShare(sb, toons);
//        sb.append(String.format("Suggested max: **%s**\n", getSuggestedMax(toons)));
//        sb.append("\n");

        Set<String> owners = new HashSet<>();
        List<ToonInfo> thisOwnersToons = new ArrayList<>();
        for (ToonInfo toon : toons) {
            owners.add(toon.getOwnerName());
        }
        for (String owner : owners) {
            thisOwnersToons.clear();
            sb.append(String.format("%s:\n", owner));
            sb.append("```");
            for (ToonInfo toon : toons) {
                if (toon.getOwnerName().equals(owner)) {
                    thisOwnersToons.add(toon);
                }
            }
            Collections.sort(thisOwnersToons, new Comparator<ToonInfo>() {
                @Override
                public int compare(ToonInfo o1, ToonInfo o2) {
                    return (int) (Double.parseDouble(o2.getToonLevel()) - Double.parseDouble(o1.getToonLevel()));
                }
            });
            for (ToonInfo toon : thisOwnersToons) {
                sb.append(String.format("%s, %s %s %s\n", toon.getToonName(), toon.getToonLevel(), toon.getToonRace(), toon.getToonClass()));
            }
            sb.append("```");
            sb.append("\n");
        }
        return sb.toString();
    }

    private String getHighLevel(List<ToonInfo> toons) {
        int max = 0;
        for (ToonInfo toon : toons) {
            double toonLevelD = Double.parseDouble(toon.getToonLevel());
            int toonLevel = (int) toonLevelD;
            if (toonLevel > max) {
                max = toonLevel;
            }
        }
        return String.valueOf(max);
    }

    private String getSuggestedMax(List<ToonInfo> toons) {
        int suggestedMax;
        int currentMax = Integer.parseInt(getHighLevel(toons));

        if (currentMax == MAX_LEVEL) {
            suggestedMax = MAX_LEVEL;
        } else {
            suggestedMax = calculateSuggestedMax(toons);
        }

        return String.valueOf(suggestedMax);
    }

    private int calculateSuggestedMax(List<ToonInfo> toons) {
        int currentMax = Integer.parseInt(getHighLevel(toons));
        int suggestedMax;
        List<Integer> levelsInRange = new ArrayList<>();
        for (ToonInfo toon : toons) {
            double toonLevelD = Double.parseDouble(toon.getToonLevel());
            int toonLevel = (int) toonLevelD;
            if (toonLevel >= (currentMax - LEVEL_RANGE_FOR_MAINS)) {
                levelsInRange.add(toonLevel);
            }
        }
        if (currentMax >= 40) {
            suggestedMax = Collections.min(levelsInRange) + 5;
        } else {
            suggestedMax = Collections.min(levelsInRange) + 3;
        }

        return suggestedMax;
    }

    private void calculateAndDisplayXpShare(StringBuilder sb, List<ToonInfo> toons) {
        List<ToonInfo> mainToons = getMainToons(toons);
        List<ToonExperience> mainToonXpList = getToonXpList(mainToons);
        double totalXpForAllToons = mainToonXpList.stream().mapToDouble(ToonExperience::getTotalExperience).sum();
        System.out.println("Total xp for all toons: " + totalXpForAllToons);
        for (ToonExperience toonXp : mainToonXpList) {
            Double experienceShare = toonXp.getTotalExperience() / totalXpForAllToons * 100;
            System.out.printf("%f / %f * 100 = %f\n", toonXp.getTotalExperience(), totalXpForAllToons, experienceShare);
            toonXp.setExperienceSharePct(MemberUtility.round(experienceShare, 2));
        }
        Collections.sort(mainToonXpList, new Comparator<ToonExperience>() {
            @Override
            public int compare(ToonExperience o1, ToonExperience o2) {
                return (int) (o2.getExperienceSharePct() - o1.getExperienceSharePct());
            }
        });
        sb.append("```");
        for (ToonExperience toonXp : mainToonXpList) {
            sb.append(String.format("%s: %.2f%%\n", toonXp.getToonInfo().getToonName(), toonXp.getExperienceSharePct()));
        }
        sb.append("```");
        sb.append("\n");
        Double smallestShare = mainToonXpList.stream().mapToDouble(ToonExperience::getExperienceSharePct).min().getAsDouble();
        System.out.println("Smallest exp share: " + smallestShare);
        //todo - calculate suggested levels
        if (mainToonXpList.stream().mapToDouble(ToonExperience::getTotalExperience).min().getAsDouble() < 30d) {

        }
    }

    private List<ToonInfo> getMainToons(List<ToonInfo> allToons) {
        List<ToonInfo> mainToons = new ArrayList<>();
        int currentMax = Integer.parseInt(getHighLevel(allToons));
        for (ToonInfo toon : allToons) {
            double toonLevelD = Double.parseDouble(toon.getToonLevel());
            int toonLevel = (int) toonLevelD;
            if (toonLevel >= (currentMax - LEVEL_RANGE_FOR_MAINS)) {
                mainToons.add(toon);
            }
        }
        return mainToons;
    }

    private List<ToonExperience> getToonXpList(List<ToonInfo> toons) {
        List<ToonExperience> toonXpList = new ArrayList<>();
        for (ToonInfo toon : toons) {
            toonXpList.add(new ToonExperience(toon));
        }
        return toonXpList;
    }
}
