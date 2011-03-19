package org.productivity.java.syslog4j.test.misc;

import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;

/**
 * @author James Wang <jianshi.wang@gmail.com>
 *
 */
public class SyslogGenerator {
	public static void main(String[] args) {
		SyslogIF syslog = Syslog.getInstance("udp");
		syslog.getConfig().setHost("192.168.100.66");
		syslog.emergency("Emergency message.");
		syslog.info("Info message.");
		syslog.critical("Critical message.");
	}
}
