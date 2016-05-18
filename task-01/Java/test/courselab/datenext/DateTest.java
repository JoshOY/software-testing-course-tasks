package courselab.datenext;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joshoy on 16/4/1.
 */
public class DateTest {

    @Test
    public void testToString() throws Exception {
        assertEquals( "2016-04-01", (new Date(2016, 4, 1).toString()) );
        assertEquals( "2016-03-31", (new Date(2016, 3, 31).toString()) );
        assertEquals( "2016-11-10", (new Date(2016, 11, 10).toString()) );
        assertEquals( "2016-10-06", (new Date(2016, 10, 6).toString()) );
        assertEquals( "Invalid Date", (new Date(2016, -1, -1).toString()) );
    }

    @Test
    public void testIsLeapYear() throws Exception {
        assertEquals(true, DateUtil.isLeapYear(2016));
        assertEquals(true, DateUtil.isLeapYear(2000));
        assertEquals(false, DateUtil.isLeapYear(1900));
        assertEquals(false, DateUtil.isLeapYear(2015));
    }

    @Test
    public void testNextDay() throws Exception {

        assertEquals( "2016-03-16", ( new Date(2016, 3, 15).nextDay().toString()) );
        assertEquals( "2016-03-02", ( new Date(2016, 3, 1).nextDay().toString()) );
        assertEquals( "2016-04-01", ( new Date(2016, 3, 31).nextDay().toString()) );
        assertEquals( "2016-05-01", ( new Date(2016, 4, 30).nextDay().toString()) );
        assertEquals( "2016-02-29", ( new Date(2016, 2, 28).nextDay().toString()) );
        assertEquals( "2016-03-01", ( new Date(2016, 2, 29).nextDay().toString()) );
        assertEquals( "2015-03-01", ( new Date(2015, 2, 28).nextDay().toString()) );
        assertEquals( "2016-01-01", ( new Date(2015, 12, 31).nextDay().toString()) );
        assertEquals( "2017-01-01", ( new Date(2016, 12, 31).nextDay().toString()) );

        assertEquals( "Invalid Date", ( new Date(1799, 5, 5).nextDay().toString()) );
        assertEquals( "Invalid Date", ( new Date(3001, 5, 5).nextDay().toString()) );
        assertEquals( "Invalid Date", ( new Date(1900, 2, 29).nextDay().toString()) );
        assertEquals( "Invalid Date", ( new Date(1996, 13, 5).nextDay().toString()) );
        assertEquals( "Invalid Date", ( new Date(2015, 2, 29).nextDay().toString()) );
        assertEquals( "Invalid Date", ( new Date(2015, 12, 32).nextDay().toString()) );
        assertEquals( "Invalid Date", ( new Date(2015, -1, -10).nextDay().toString()) );
    }


}