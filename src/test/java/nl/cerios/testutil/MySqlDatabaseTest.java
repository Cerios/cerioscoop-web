package nl.cerios.testutil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.Before;
import org.mockito.Spy;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * @author rsanders
 *
 * TODO als er geen database is met de naam "cerioscoop_db", dat hij die dan nieuw maakt
 */
public class MySqlDatabaseTest {

	private static final String OPTION_CREATE = "create";
	private static final String OPTION_DROP = "drop";
	private static final String OPTION_SHUTDOWN = "shutdown";
	protected static final Properties PROPS;

	static {
		final URL propertyDataURL = MySqlDatabaseTest.class.getResource("/mysql-database-test.properties");
		try (final InputStream propertyDataStream = propertyDataURL.openStream()) {
			PROPS = new Properties();
			PROPS.load(propertyDataStream);
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	@Spy
	private static DataSource dataSource = getDataSource();

	protected static DataSource getDataSource() {
		final MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(PROPS.getProperty("db.user"));
		dataSource.setPassword(PROPS.getProperty("db.password"));
		dataSource.setDatabaseName(PROPS.getProperty("db.name"));
		return dataSource;
	}

//	private static void applyDatabaseOption(final String dbOption) throws SQLException {
//		
//		try {
//			final MysqlDataSource dataSource = (MysqlDataSource) getDataSource();
//			dataSource.getCreateDatabaseIfNotExist();
//			dataSource.setDatabaseName(dataSource.getDatabaseName() + ";" + dbOption + "=true");
//			dataSource.getConnection();
//		} catch (SQLException e) {
//			// Wow, this is a dirty tricky piece of code! See
//			// http://stackoverflow.com/questions/2307788/how-to-shutdown-derby-in-memory-database-properly
//			final String dbState = e.getSQLState();
//			if (!dbState.equals("XJ004") && !dbState.equals("XJ015") && !dbState.equals("08006")) {
//				throw e;
//			}
//		}
//	}
	
	@Before
	public void initDatabase() throws IOException, SQLException {
		// Remove any existing database schema.
		//applyDatabaseOption(OPTION_DROP);

		// Tell Derby to create the schema...
		//applyDatabaseOption(OPTION_CREATE);
		
		// ...and run SQL scripts
		try (final Connection connection = getDataSource().getConnection();
				final Statement sqlStatement = connection.createStatement()) {

			final String[] scriptResourceNames = new String[] { PROPS.getProperty("db.script.create"),
					PROPS.getProperty("db.script.testData") };

			for (String scriptResourceName : scriptResourceNames) {
				try (InputStream scriptStream = DerbyDatabaseTest.class.getResourceAsStream(scriptResourceName)) {
					runSqlScript(sqlStatement, scriptStream);
				}
			}
		}
	}

	private static void runSqlScript(final Statement sqlStatement, final InputStream scriptInputStream)
			throws SQLException {
		try (final Scanner inputScanner = new Scanner(scriptInputStream)) {
			inputScanner.useDelimiter(";");
			while (inputScanner.hasNext()) {
				final String line = inputScanner.next().trim();
				if (!line.isEmpty()) {
					sqlStatement.execute(line);
				}
			}
		}
	}

	@AfterClass
	public static void shutdownDatabase() throws SQLException {
		// Stop the database instance.
		//applyDatabaseOption(OPTION_SHUTDOWN);
	}
}
