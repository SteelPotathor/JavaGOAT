package com.example.javagoat.back;

import java.io.Serializable;
import java.util.*;

public class Passion implements Serializable, Cloneable {

    public HashSet<video_games> passionVG = new HashSet<>();
    public HashSet<miscellaneous> passionM = new HashSet<>();

    private static final Random random = new Random();


    public enum video_games {
        LEAGUE_OF_LEGENDS,
        VALORANT,
        MINECRAFT,
        TERRARIA,
        CALL_OF_DUTY,
        DESTINY_2,
        DIABLO_3,
        WORLD_OF_WARCRAFT,
        HEARTHSTONE,
        BATTLEFIELD,
        APEX_LEGENDS,
        DOTA_2,
        CS_GO,
        SUPER_SMASH_BROS,
        FORTNITE,
        JACKBOX_PARTY,
        DIVINE_KNOCKOUT,
        AMOGUS,
        STREET_FIGHTER,
        ARK,
        CRAB_GAME,
        BRAWLHALLA,
        MONSTER_HUNTER,
        DRAGON_QUEST,
        GTA,
        GENSHIN_IMPACT,
        SEA_OF_THIEVES,
        DEAD_BY_DAYLIGHT,
        THE_DOORS,
        FALL_GUYS,
        ROCKET_LEAGUE,
        WARFRAME,
        PUBG,
        VALHEIM,
        RUST;

        private static final List<video_games> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static video_games randomVideoGame() {
            return VALUES.get(random.nextInt(SIZE));
        }

    }

    public enum miscellaneous {
        BIKE,
        MOVIES,
        ANIME,
        MANGA,
        NETFLIX,
        COOKING,
        RUNNING,
        DANCING,
        YOGA,
        TRAVELING,
        MODE,
        GYM,
        BASKET,
        TENNIS,
        SOCCER,
        MARTIAL_ARTS,
        LITERATURE,
        DIVING,
        WRITING,
        PROGRAMMING,
        PHOTOGRAPHY,
        GARDENING,
        HISTORY,
        GEOGRAPHY,
        VACATIONS,
        MUSIC,
        PAINTING,
        PORN,
        SLEEP,
        ANIMALS,
        SPIRITUALITY,
        GRINDING,
        PLAYING_WITH_KIDS,
        CHESS,
        COSPLAY,
        FURRIES,
        PSYCHOLOGY,
        PHILOSOPHY,
        SCIENCES,
        THEATER,
        VLOGGING,
        CLIMBING,
        NUDISM,
        DECORATIONS;

        private static final List<miscellaneous> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();

        public static miscellaneous randomMiscellaneous() {
            return VALUES.get(random.nextInt(SIZE));
        }
    }

    // Customised Passion -> Add Profile
    public Passion(HashSet<video_games> pVG, HashSet<miscellaneous> pM) {
        this.passionVG = pVG;
        this.passionM = pM;
    }

    // Random Passion
    public Passion() {
    }

    public void setRandomPassion() {
        while (this.passionVG.size() < random.nextInt(2, 6)) {
            this.passionVG.add(video_games.randomVideoGame());
        }
        while (this.passionM.size() < random.nextInt(2, 6)) {
            this.passionM.add(miscellaneous.randomMiscellaneous());
        }
    }

    @Override
    public Passion clone() throws CloneNotSupportedException {
        Passion clone = (Passion) super.clone();
        clone.passionVG = (HashSet<video_games>) passionVG.clone();
        clone.passionM = (HashSet<miscellaneous>) passionM.clone();
        return clone;
    }

    public HashSet<video_games> getPassionVG() {
        return passionVG;
    }

    public void setPassionVG(HashSet<video_games> passionVG) {
        this.passionVG = passionVG;
    }

    public HashSet<miscellaneous> getPassionM() {
        return passionM;
    }

    public void setPassionM(HashSet<miscellaneous> passionM) {
        this.passionM = passionM;
    }

    @Override
    public String toString() {
        String sb = "Passion{" + "passionVG=" + passionVG +
                ", passionM=" + passionM +
                '}';
        return sb;
    }

}
