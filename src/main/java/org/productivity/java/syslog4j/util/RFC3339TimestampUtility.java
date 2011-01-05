package org.productivity.java.syslog4j.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* <p>ISO3339TimestampUtility is adapted from code provided at:</p>
* 
* <code>http://hatori42.com/RFC3339Date.txt</code>
* 
* <p>As requested, the developers notes are provided as a comment at
* the end of this class.</p>
* 
* <p>Syslog4j is licensed under the Lesser GNU Public License v2.1.  A copy
* of the LGPL license is available in the META-INF folder in all
* distributions of Syslog4j and in the base directory of the "doc" ZIP.</p>
* 
* @author &lt;syslog4j@productivity.org&gt;
* @version $Id: RFC3339TimestampUtility.java,v 1.2 2010/02/04 03:52:06 cvs Exp $
*/

public final class RFC3339TimestampUtility {
	protected static final String SIMPLEDATE_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	protected static final String SIMPLEDATE_FORMAT_2 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";
	protected static final String SIMPLEDATE_FORMAT_3 = "yyyy-MM-dd'T'HH:mm:ssZ";
	protected static final String SIMPLEDATE_FORMAT_4 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ";
	
	public static Date parse(final String _datestring) throws ParseException, IndexOutOfBoundsException {
		Date date = null;
		
		String datestring = _datestring;

	    if (datestring.endsWith("Z")) {
	    	try {
	    		SimpleDateFormat s = new SimpleDateFormat(SIMPLEDATE_FORMAT_1);
	    		
	    		date = s.parse(datestring);
	    		
	    	} catch (ParseException pe) {
	    		SimpleDateFormat s = new SimpleDateFormat(SIMPLEDATE_FORMAT_2);
	    		s.setLenient(true);

	    		date = s.parse(datestring);		  
	    	}
	    	
	    	return date;
	    }

	    String firstpart = datestring.substring(0,datestring.lastIndexOf('-'));
	    String secondpart = datestring.substring(datestring.lastIndexOf('-'));
	    
	    secondpart = secondpart.substring(0,secondpart.indexOf(':')) + secondpart.substring(secondpart.indexOf(':') + 1);
	    
	    datestring  = firstpart + secondpart;
	    
	    try {
	    	SimpleDateFormat s = new SimpleDateFormat(SIMPLEDATE_FORMAT_3);	

	    	date = s.parse(datestring);
	    	
	    } catch(ParseException pe) {
	    	SimpleDateFormat s = new SimpleDateFormat(SIMPLEDATE_FORMAT_4);
	    	s.setLenient(true);

	    	date = s.parse(datestring);
	    }
	    
	    return date;
	}
}

/*
	### Developer's Original Notes (Taken on 2/2/2010 from http://hatori42.com/RFC3339Date.txt) ###
	### Permission granted to include code in Syslog4j (via e-mail on 2/3/2010) -- thanks, Chad! ###

	"I was working on an Atom (http://www.w3.org/2005/Atom) parser and discovered that I 
	could not parse dates in the format defined by RFC 3339 using the  SimpleDateFormat 
	class. The  reason was the ':' in the time  zone. This code strips out the colon if 
	it's there and tries four different formats on the resulting string depending on if
	it has a  time zone, or if it has a  fractional second part.  There is a probably a 
	better way  to do this, and a more proper way.  But this is a really small addition 
	to a  codebase  (You don't  need a jar, just throw  this  function in  some  static 
	Utility class if you have one).

	Feel free to use this in your code, but I'd appreciate it if you keep this note  in 
	the code if you distribute it.  Thanks!

	For  people  who might  be  googling: The date  format  parsed  by  this  goes  by: 
	atomDateConstruct,  xsd:dateTime,  RFC3339  and  is compatable with: ISO.8601.1988, 
	W3C.NOTE-datetime-19980827  and  W3C.REC-xmlschema-2-20041028 (that I know of)

	Copyright 2007, Chad Okere (ceothrow1 at gmail dotcom)
	OMG NO WARRENTY EXPRESSED OR IMPLIED!!!1
*/
