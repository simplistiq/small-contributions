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

/**
 * 
 * @author Pandits
 * @version 1.0
 */
public interface TimeUtil {

    public Date getAdjustedDateInTimeZone(Date date, TimeZone timeZone);

    public Date advanceToNextDayMidnight(Date date);

    public Date retrogressToThisDayMidnight(Date date);

    public Date advanceBy(Date date, int numOfDays);

    public Date retrogessBy(Date date, int numOfDays);

    public int spanIntervalInDays(Date startDate, Date endDate);

    public int spanIntervalInWeeks(Date startDate, Date endDate);

    public int spanIntervalInHours(Date startDate, Date endDate);

    public int getDayOfTheWeek(long utcTimeInMillis);

    public int getDayOfTheWeek(long timeInMillis, TimeZone timeZone);

    public int getHourOfTheDay(long utcTimeInMillis);

    public int getHourOfTheDay(long timeInMillis, TimeZone timeZone);

    public int getMinuteOfTheDay(long utcTimeInMillis);

    public int getMinuteOfTheDay(long timeInMillis, TimeZone timeZone);

    public long getSecondsOfDayInMillis(long utcTimeInMillis);

    public long getSecondsOfDayInMillis(long timeInMillis, TimeZone timeZone);
}
