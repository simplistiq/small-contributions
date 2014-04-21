/**

Copyright (c) 2014 Pandits

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

 */

package com.pandits.opensource;

import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDateTime;
import org.joda.time.Weeks;

/**
 * 
 * @author Pandits
 * @version 1.0
 */
public class JodaTimeUtil implements TimeUtil {

    private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");

    public Date getAdjustedDateInTimeZone(Date date, TimeZone timeZone) {
        LocalDateTime dateTime = createLocalDateTime(date.getTime(), timeZone);
        return dateTime.toDate();
    }

    public int spanIntervalInDays(Date startDate, Date endDate) {
        DateTime startDateTime = createDateTime(startDate);
        DateTime endDateTime = createDateTime(endDate);

        return Days.daysBetween(startDateTime, endDateTime).getDays();
    }

    public int spanIntervalInWeeks(Date startDate, Date endDate) {
        DateTime startDateTime = createDateTime(startDate);
        DateTime endDateTime = createDateTime(endDate);

        return Weeks.weeksBetween(startDateTime, endDateTime).getWeeks();
    }

    public int spanIntervalInHours(Date startDate, Date endDate) {
        DateTime startDateTime = createDateTime(startDate);
        DateTime endDateTime = createDateTime(endDate);

        return Hours.hoursBetween(startDateTime, endDateTime).getHours();
    }

    public Date retrogressToThisDayMidnight(Date date) {
        DateTime dateTime = createDateTime(date);
        return dateTime.withTimeAtStartOfDay().toDate();
    }

    public Date advanceToNextDayMidnight(Date date) {
        DateTime dateTime = createDateTime(date);
        DateTime nextDayMidnight = dateTime.withTimeAtStartOfDay().plusDays(1);
        return nextDayMidnight.toDate();
    }

    public Date advanceBy(Date date, int numOfDays) {
        DateTime dateTime = createDateTime(date);
        return dateTime.plusDays(numOfDays).toDate();
    }

    public Date retrogessBy(Date date, int numOfDays) {
        DateTime dateTime = createDateTime(date);
        return dateTime.minusDays(numOfDays).toDate();
    }

    public int getDayOfTheWeek(long utcTimeInMillis) {
        return getDayOfTheWeek(utcTimeInMillis, UTC_TIME_ZONE);
    }

    public int getDayOfTheWeek(long timeInMillis, TimeZone timeZone) {
        DateTime dateTime = createDateTime(timeInMillis, timeZone);
        return dateTime.getDayOfWeek();
    }

    public int getHourOfTheDay(long utcTimeInMillis) {
        return getHourOfTheDay(utcTimeInMillis, UTC_TIME_ZONE);
    }

    public int getHourOfTheDay(long timeInMillis, TimeZone timeZone) {
        DateTime dateTime = createDateTime(timeInMillis, timeZone);
        return dateTime.getHourOfDay();
    }

    public int getMinuteOfTheDay(long utcTimeInMillis) {
        return getMinuteOfTheDay(utcTimeInMillis, UTC_TIME_ZONE);
    }

    public int getMinuteOfTheDay(long timeInMillis, TimeZone timeZone) {
        DateTime dateTime = createDateTime(timeInMillis, timeZone);
        return dateTime.getMinuteOfDay();
    }

    public long getSecondsOfDayInMillis(long utcTimeInMillis) {
        return getSecondsOfDayInMillis(utcTimeInMillis, UTC_TIME_ZONE);
    }

    public long getSecondsOfDayInMillis(long timeInMillis, TimeZone timeZone) {
        DateTime dateTime = createDateTime(timeInMillis, timeZone);
        return dateTime.getMillisOfDay();
    }

    private DateTime createDateTime(long timeInMillis, TimeZone timeZone) {
        DateTimeZone dateTimeZone = findTimeZone(timeZone);
        DateTime dateTime = createDateTime(timeInMillis, dateTimeZone);
        return dateTime;
    }

    private LocalDateTime createLocalDateTime(long timeInMillis, TimeZone timeZone) {
        DateTimeZone dateTimeZone = findTimeZone(timeZone);
        LocalDateTime localDateTime = new LocalDateTime(timeInMillis, dateTimeZone);
        return localDateTime;
    }

    private DateTimeZone findTimeZone(TimeZone timeZone) {
        return DateTimeZone.forTimeZone(timeZone);
    }

    private DateTime createDateTime(long timeInMillis, DateTimeZone dateTimeZone) {
        return new DateTime(timeInMillis, dateTimeZone);
    }

    private DateTime createDateTime(Date startDate) {
        return new DateTime(startDate.getTime(), DateTimeZone.UTC);
    }
}
