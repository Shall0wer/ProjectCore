package fr.shall0wer.project.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Made by THE_BATTEUR <3
 */

public class ConvertTime {

    private long time;
    private LocalDateTime ldt;
    private boolean syntaxe;

    public ConvertTime(String toConvert) {
        if(toConvert.endsWith("y")) {
            try {
                int t = Integer.parseInt(toConvert.replaceAll("y",""));
                this.ldt = LocalDateTime.now().plusYears(t);
                this.time = t * 31536000000L;
                this.syntaxe = true;
            } catch (NumberFormatException e) {
                this.syntaxe = false;
            }
        } else if(toConvert.endsWith("mo")) {
            try {
                int t = Integer.parseInt(toConvert.replaceAll("mo",""));
                this.ldt = LocalDateTime.now().plusMonths(t);
                this.time = t * 2628000000L;
                this.syntaxe = true;
            } catch (NumberFormatException e) {
                this.syntaxe = false;
            }
        } else if(toConvert.endsWith("d")) {
            try {
                int t = Integer.parseInt(toConvert.replaceAll("d",""));
                this.ldt = LocalDateTime.now().plusDays(t);
                this.time = t * 86400000L;
                this.syntaxe = true;
            } catch (NumberFormatException e) {
                this.syntaxe = false;
            }
        } else if(toConvert.endsWith("h")) {
            try {
                int t = Integer.parseInt(toConvert.replaceAll("h",""));
                this.ldt = LocalDateTime.now().plusDays(t);
                this.time = t * 3600000L;
                this.syntaxe = true;
            } catch (NumberFormatException e) {
                this.syntaxe = false;
            }
        } else if(toConvert.endsWith("m")) {
            try {
                int t = Integer.parseInt(toConvert.replaceAll("m",""));
                this.ldt = LocalDateTime.now().plusMinutes(t);
                this.time = t * 60000L;
                this.syntaxe = true;
            } catch (NumberFormatException e) {
                this.syntaxe = false;
            }
        } else if(toConvert.endsWith("s")) {
            try {
                int t = Integer.parseInt(toConvert.replaceAll("s",""));
                this.ldt = LocalDateTime.now().plusSeconds(t);
                this.time = t * 1000L;
                this.syntaxe = true;
            } catch (NumberFormatException e) {
                this.syntaxe = false;
            }
        } else {
            this.syntaxe = false;
        }
    }

    public ConvertTime(long toConvert) {
        this.time = toConvert + 3600000L;
        this.ldt = LocalDateTime.now().plusSeconds((toConvert-System.currentTimeMillis())/1000);
    }

    public long getTimeMillis() {
        return time;
    }

    public long getTimeSeconds() {
        return time/1000;
    }

    public LocalDateTime getDateTime() {
        return ldt;
    }

    public String getDateTimeFormatted() {
        return this.ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm:ss"));
    }

    public boolean syntaxeIsCorrect() {
        return syntaxe;
    }
}
