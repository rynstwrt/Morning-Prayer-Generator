package ryanstewartalex.pw.morningprayerrevisited;

import org.joda.time.DateTime;

/** NOTES
 * -----------------------------------
 *
 * Advent: 4th sunday before christmas to christmas eve
 *
 * Christmas: Dec 25
 *
 * Epiphany: Jan 6
 *
 * Lent: Ash Wednesday to Easter Eve
 *
 * Holy Week: Final week of Lent
 *
 * Easter Season: Easter to Pentecost
 *
 * Easter: Sunday after/on vernal equinox
 *
 * Pentecost: 50 (49) days after easter
 *
 * Ascension Day: 40th day of easter (Easter + 39 days)
 *
 * Trinity Sunday: First sunday after pentecost
 *
 * All saints day: Nov 1
 *
 * Thanksgiving: dates when you're thankful
 *
 * Feasts of Incarnation: Dec 25
 *
 * Baptism of Christ: first sunday after epiphany
 *
 * Feasts of Transfiguration: August 6
 *
 * Feast of the cross: Sep 14
 *
 *
 */


public class ChristianEvents {

    DateTime date;

    ChristianEvents() {
        date = new DateTime();
    }

    private DateTime createDay(int month, int day) {
        return new DateTime(date.getYear(), month, day, date.getHourOfDay(), date.getMinuteOfHour(), date.getSecondOfMinute(), date.getMillisOfSecond());
    }

    private boolean isBetween(DateTime first, DateTime second) { //both values inclusive
        return (first.minusDays(1).isBefore(date) && second.plusDays(1).isAfter(date));
    }

    private DateTime getEaster() {

        int year = date.getYear();

        int a = year % 19,
                b = year / 100,
                c = year % 100,
                d = b / 4,
                e = b % 4,
                g = (8 * b + 13) / 25,
                h = (19 * a + b - d - g + 15) % 30,
                j = c / 4,
                k = c % 4,
                m = (a + 11 * h) / 319,
                r = (2 * e + 2 * j - k - h + m + 32) % 7,
                n = (h - m + r + 90) / 25,
                p = (h - m + r + n + 19) % 32;

        return createDay(n, p);
    }

    public boolean inAdvent() {
        DateTime c = createDay(12, 25);
        DateTime m = c.minusWeeks(4);

        while (m.getDayOfWeek() != 7) {
            m = m.plusDays(1);
        }

        return (isBetween(m, createDay(12, 24)));
    }

    public boolean isChristmas() {
        return date.equals(createDay(12, 25));
    }

    public boolean isEpiphany() {
        return date.equals(createDay(1, 6));
    }

    public boolean inLent() {
        return isBetween(getEaster().minusDays(46), getEaster().minusDays(1));
    }

    public boolean inHolyWeek() { //final week of lent
        return isBetween(getEaster().minusDays(7), getEaster().minusDays(1));
    }

    public boolean inEasterSeasonIncludingAscensionDay() {
        return isBetween(getEaster(), getEaster().plusDays(49));
    }

    public boolean isTrinitySunday() {
        return date.equals(getEaster().plusDays(49));
    }

    public boolean isAllSaints() {
        return date.equals(createDay(11, 1));
    }


}




