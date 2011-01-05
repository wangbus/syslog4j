package org.productivity.java.syslog4j.test.util;

import java.util.Date;

import junit.framework.TestCase;

import org.productivity.java.syslog4j.util.RFC3339TimestampUtility;

/**
* ISO3339TimestampUtilityTest verifies that RFC3339TimestampUtility works correctly.
* 
* <p>Syslog4j is licensed under the Lesser GNU Public License v2.1.  A copy
* of the LGPL license is available in the META-INF folder in all
* distributions of Syslog4j and in the base directory of the "doc" ZIP.</p>
* 
* @author &lt;syslog4j@productivity.org&gt;
* @version $Id: RFC3339TimestampUtilityTest.java,v 1.3 2010/03/26 03:06:57 cvs Exp $
*/
public class RFC3339TimestampUtilityTest extends TestCase {
	public static void assertParse(String dateString, long time) throws Exception {
		Date date = RFC3339TimestampUtility.parse(dateString);
		
		long dateTime = date.getTime();
		
		assertEquals(time,dateTime);
	}
	
	public void testRFC3339TimestampUtility() throws Exception {
		String timeZone = "-06:00";
		
		// Local Zone tests
		assertParse("2007-05-01T15:43:26" + timeZone,1178055806000l);
		assertParse("2007-05-01T15:43:26.3" + timeZone,1178055806003l);
		assertParse("2007-05-01T15:43:26.3452" + timeZone,1178055809452l);
        //assertParse("2007-01-05T15:43:26.345200" + timeZone,1178055809452l);
        //2011-01-05T14:52:06.1248+01:00

		// GMT (Z) tests
		assertParse("2007-05-01T15:43:26.3452Z",1178052209452l);
		assertParse("2007-05-01T15:43:26.3Z",1178052206003l);
		assertParse("2007-05-01T15:43:26Z",1178052206000l);


	}
}
