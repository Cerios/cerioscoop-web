package nl.cerios.testutil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * @author rsanders
 *
 * TODO Create and Drop Database "cerioscoop_db", zorg dat er een cerioscoop_db aanwezig is!
 *  
 */
public class MySqlDatabaseTest {
	private static final Logger LOG = LoggerFactory.getLogger(SeleniumTest.class);
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
	
	@BeforeClass
	public static void initDatabase() throws IOException, SQLException {
		if(SeleniumTest.isWebServerRunning()){
			// ...and run SQL scripts
			try (final Connection connection = getDataSource().getConnection();
					final Statement sqlStatement = connection.createStatement()) {
	
				final String[] scriptResourceNames = new String[] { PROPS.getProperty("db.script.create"),
						PROPS.getProperty("db.script.testData") };
	
				for (String scriptResourceName : scriptResourceNames) {
					try (InputStream scriptStream = MySqlDatabaseTest.class.getResourceAsStream(scriptResourceName)) {
						runSqlScript(sqlStatement, scriptStream);
					}
				}
			}
		}else{
			LOG.error("WebServer is not running!");
			return;
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
}
