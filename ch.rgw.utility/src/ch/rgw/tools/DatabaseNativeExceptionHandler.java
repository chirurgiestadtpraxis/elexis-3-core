package ch.rgw.tools;

import static ch.rgw.tools.JdbcLink.DBFLAVOR_H2;
import static ch.rgw.tools.JdbcLink.DBFLAVOR_MYSQL;
import static ch.rgw.tools.JdbcLink.DBFLAVOR_POSTGRESQL;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Natively handles SQL Exceptions if possible. E.g. if we try to update a
 * database sometimes a "column already available" exception might occur
 * constantly preventing us from finishing the update - although not necessary.
 * This class provides advise to the question whether an exception is really
 * necessary to be thrown.
 */
public class DatabaseNativeExceptionHandler {

	private static Logger log = LoggerFactory
			.getLogger(DatabaseNativeExceptionHandler.class);

	/**
	 * Try to handle the thrown Exception considering the native Database
	 * implementation
	 * 
	 * @param dBFlavor
	 * @param e
	 * @return <code>true</code> if we can not mitigate the problem and an
	 *         exception should be thrown, else <code>false</code>
	 */
	public static boolean handleException(String dBFlavor, SQLException e) {
		switch (dBFlavor) {
		case DBFLAVOR_MYSQL:
			return handleMysqlException(e);
		case DBFLAVOR_POSTGRESQL:
			return handlePostgresException(e);
		case DBFLAVOR_H2:
			return handleH2Exception(e);
		default:
			log.error("Unknown database flavor: " + dBFlavor);
			break;
		}
		return true;
	}

	// ----------- H2
	private static boolean handleH2Exception(SQLException e) {
		return true;
	}

	// ----------- MYSQL
	private static boolean handleMysqlException(SQLException e) {
		return true;
	}

	// ----------- POSTGRES
	public static final String POSTGRES_ERRORCODE_DUPLICATE_COLUMN = "42701";
	public static final String POSTGRES_ERRORCODE_DUPLICATE_TABLE = "42P07";

	/**
	 * @param e
	 * @return
	 * @see http://www.postgresql.org/docs/9.1/static/errcodes-appendix.html
	 */
	private static boolean handlePostgresException(SQLException e) {
		String sqlState = e.getSQLState();
		switch (sqlState) {
		case POSTGRES_ERRORCODE_DUPLICATE_TABLE:
		case POSTGRES_ERRORCODE_DUPLICATE_COLUMN:
			log.info("Found duplicate element, mitigating error code "
					+ sqlState + ".");
			return false;
		default:
		}

		return true;
	}

}
