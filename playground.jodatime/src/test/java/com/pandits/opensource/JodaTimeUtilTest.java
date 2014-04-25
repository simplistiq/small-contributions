package com.pandits.opensource;

import java.util.Date;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JodaTimeUtilTest {

    private JodaTimeUtil jodaTimeUtil;

    @Before
    public void before() {
        jodaTimeUtil = new JodaTimeUtil();
    }

    @Test
    public void getDateInTimeZone() {
        long epochTime = 1398038663000L;
        TimeZone timeZone = TimeZone.getTimeZone("Pacific/Honolulu");
        Date date = new Date(epochTime);
        Date timeZoneSpecificDate = jodaTimeUtil.getAdjustedDateInTimeZone(date, timeZone);

        Assert.assertNotNull(timeZoneSpecificDate);
    }

    @Test
    public void getDayOfTheWeek() {
        long epochTime = 1398038663000L;
        int dayOfWeek = jodaTimeUtil.getDayOfTheWeek(epochTime);
        Assert.assertEquals(1, dayOfWeek);
    }

    @Test
    public void getDayOfTheWeekForTimeZone() {
        long epochTime = 1398038663000L;
        int dayOfWeek = jodaTimeUtil.getDayOfTheWeek(epochTime,
                TimeZone.getTimeZone("America/Los_Angeles"));
        Assert.assertEquals(7, dayOfWeek);
    }

    @Test
    public void getTimeOfDayInMillis() {
        long epochTime = 1398038663000L;
        long timeOfDay = jodaTimeUtil.getSecondsOfDayInMillis(epochTime);

        long expectedTimeOfTheDay = (4L * 60 + 23) * 1000;
        Assert.assertEquals(expectedTimeOfTheDay, timeOfDay);
    }

    @Test
    public void getHourOfTheDay() {
        long epochTime = 1398049773000L;
        long hourOfDay = jodaTimeUtil.getHourOfTheDay(epochTime,
                TimeZone.getTimeZone("America/Los_Angeles"));

        long hourOfTheDay = 20L;
        Assert.assertEquals(hourOfTheDay, hourOfDay);
    }

    @Test
    public void getHourOfTheDayForTimeZone() {
        long epochTime = 1398049773000L;
        long hourOfDay = jodaTimeUtil.getHourOfTheDay(epochTime);

        long hourOfTheDay = 3L;
        Assert.assertEquals(hourOfTheDay, hourOfDay);
    }

    @Test
    public void getMinuteOfTheDay() {
        long epochTime = 1398049773000L;
        long hourOfDay = jodaTimeUtil.getMinuteOfTheDay(epochTime);

        long hourOfTheDay = 189L;
        Assert.assertEquals(hourOfTheDay, hourOfDay);
    }

    @Test
    public void getMinuteOfTheDayForTimeZone() {
        long epochTime = 1398049773000L;
        long hourOfDay = jodaTimeUtil.getMinuteOfTheDay(epochTime,
                TimeZone.getTimeZone("America/Los_Angeles"));

        long hourOfTheDay = 1209L;
        Assert.assertEquals(hourOfTheDay, hourOfDay);
    }

    @Test
    public void getTimeOfDayInMillisForTimeZone() {
        long epochTime = 1398038663000L;
        long timeOfDay = jodaTimeUtil.getSecondsOfDayInMillis(epochTime,
                TimeZone.getTimeZone("America/Los_Angeles"));

        long expectedTimeOfTheDay = ((17L * 60 * 60) + (4L * 60) + 23) * 1000;
        Assert.assertEquals(expectedTimeOfTheDay, timeOfDay);
    }

    @Test
    public void spanIntervalInDays() {
        long epochTimeDayOne = 1398038663000L;
        long epochTimeDayThree = 1398038663000L + 2L * 24 * 60 * 60 * 1000;
        Date startDate = new Date(epochTimeDayOne);
        Date endDate = new Date(epochTimeDayThree);
        int numberOfDays = jodaTimeUtil.spanIntervalInDays(startDate, endDate);
        Assert.assertEquals(2, numberOfDays);
    }

    @Test
    public void spanIntervalInDaysSameDay() {
        long epochTimeDayOne = 1398038663000L;
        long epochTimeDaySameDayOne = 1398038663000L + (15L * 60 * 1000);
        Date startDate = new Date(epochTimeDayOne);
        Date endDate = new Date(epochTimeDaySameDayOne);
        int numberOfDays = jodaTimeUtil.spanIntervalInDays(startDate, endDate);
        Assert.assertEquals(0, numberOfDays);
    }

    @Test
    public void spanIntervalInWeeks() {
        long epochTimeDayOne = 1398038663000L;
        long epochTimeDayTwentyTwo = 1398038663000L + 22L * 24 * 60 * 60 * 1000;
        Date startDate = new Date(epochTimeDayOne);
        Date endDate = new Date(epochTimeDayTwentyTwo);
        int numberOfWeeks = jodaTimeUtil.spanIntervalInWeeks(startDate, endDate);
        Assert.assertEquals(3, numberOfWeeks);
    }

    @Test
    public void spanIntervalInWeeksSameWeek() {
        long epochTimeDayOne = 1398038663000L;
        long epochTimeDayThree = 1398038663000L + 3L * 24 * 60 * 60 * 1000;
        Date startDate = new Date(epochTimeDayOne);
        Date endDate = new Date(epochTimeDayThree);
        int numberOfWeeks = jodaTimeUtil.spanIntervalInWeeks(startDate, endDate);
        Assert.assertEquals(0, numberOfWeeks);
    }

    @Test
    public void spanIntervalInHours() {
        long epochTimeDayOne = 1398038663000L;
        long epochTimeDayOneAdvanceByFiveHours = 1398038669000L + 15L * 60 * 60 * 1000;
        Date startDate = new Date(epochTimeDayOne);
        Date endDate = new Date(epochTimeDayOneAdvanceByFiveHours);
        int numberOfHours = jodaTimeUtil.spanIntervalInHours(startDate, endDate);
        Assert.assertEquals(15, numberOfHours);
    }

    @Test
    public void spanIntervalInHoursLessThanOne() {
        long epochTimeDayOne = 1398038663000L;
        long epochTimeDayOneAdvanceByFiftyNineMinutes = 1398038663000L + 59L * 60 * 1000;
        Date startDate = new Date(epochTimeDayOne);
        Date endDate = new Date(epochTimeDayOneAdvanceByFiftyNineMinutes);
        int numberOfHours = jodaTimeUtil.spanIntervalInHours(startDate, endDate);
        Assert.assertEquals(0, numberOfHours);
    }

    @Test
    public void advanceToNextDayMidnight() {
        long epochTimeDayOne = 1398038663000L;
        Date date = new Date(epochTimeDayOne);
        Date midnightDate = jodaTimeUtil.advanceToNextDayMidnight(date);
        Assert.assertNotNull(midnightDate);

        long nextDayMidnight = 1398124800000L;
        Assert.assertEquals(nextDayMidnight, midnightDate.getTime());
    }

    @Test
    public void retrogressToThisDayMidnight() {
        long epochTimeDayOne = 1398225600000L;
        Date date = new Date(epochTimeDayOne);
        Date midnightDate = jodaTimeUtil.retrogressToThisDayMidnight(date);
        Assert.assertNotNull(midnightDate);

        long nextDayMidnight = 1398211200000L;
        Assert.assertEquals(nextDayMidnight, midnightDate.getTime());
    }

    @Test
    public void advanceBy() {
        long epochTimeDayOne = 1398225600000L;
        Date date = new Date(epochTimeDayOne);
        int numOfDays = 2;
        Date nextDayDate = jodaTimeUtil.advanceBy(date, numOfDays);
        Assert.assertNotNull(nextDayDate);

        long nextDay = epochTimeDayOne + 2 * 24L * 60 * 60 * 1000;
        Assert.assertEquals(nextDay, nextDayDate.getTime());
    }

    @Test
    public void retrogressBy() {
        long epochTimeDayOne = 1398225600000L;
        Date date = new Date(epochTimeDayOne);
        int numOfDays = 2;
        Date nextDayDate = jodaTimeUtil.retrogessBy(date, numOfDays);
        Assert.assertNotNull(nextDayDate);

        long nextDay = epochTimeDayOne - 2 * 24L * 60 * 60 * 1000;
        Assert.assertEquals(nextDay, nextDayDate.getTime());
    }
}
