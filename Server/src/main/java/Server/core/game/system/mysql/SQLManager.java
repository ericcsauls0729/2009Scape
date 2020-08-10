package core.game.system.mysql;

import core.ServerConstants;
import core.game.system.SystemLogger;
import core.game.system.SystemManager;
import core.game.system.mysql.impl.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the SQL connections.
 * @author Vexia
 * 
 */
public final class SQLManager {
	
	/**
	 * If the sql manager is locally hosted.
	 */
	public static final boolean LOCAL = false;

	public static String SQL_SERVER_ADDRESS;

	/**
	 * The username of the user.
	 */
	public static String USERNAME;

	/**
	 * The password of the user.
	 */
	public static String PASSWORD;

	public static String PORT;

    /**
     * The database URL.
     */
    public static String DATABASE_URL;

	/**
	 * IF the sql manager is initialized.
	 */
	private static boolean initialized;

	/**
	 * Constructs a new {@code SQLManager} {@code Object}
	 */
	public SQLManager() {
		/**
		 * empty.
		 */
	}

	/**
	 * Initializes the sql manager.
	 */
	public static void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			initialized = false;
			return;
		}
		initialized = true;
		SystemManager.getSystemConfig().parse();
	}
	
	/**
	 * Pre-plugin parsing.
	 */
	public static void prePlugin() {
			//new NPCConfigSQLHandler().parse();

	}

	/**
	 * Parses data from the database for the server post plugin loading.
	 */
	public static void postPlugin() {

	}

	/**
	 * Gets a connection.
	 * @return The connection.
	 */
	public static Connection getConnection() {
		DATABASE_URL  = SQL_SERVER_ADDRESS + ":" + PORT + "/" + ServerConstants.DATABASE_NAME;
		try {
			return DriverManager.getConnection("jdbc:mysql://" +   DATABASE_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			SystemLogger.error(SQLManager.class, "Error: Mysql error message=" + e.getMessage() + ".");
		}
		return null;
	}

	/**
	 * Releases the connection so it's available for usage.
	 * @param connection The connection.
	 */
	public static void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the initialized.
	 * @return the initialized
	 */
	public static boolean isInitialized() {
		return initialized;
	}

	/**
	 * Sets the bainitialized.
	 * @param initialized the initialized to set.
	 */
	public static void setInitialized(boolean initialized) {
		SQLManager.initialized = initialized;
	}

}
