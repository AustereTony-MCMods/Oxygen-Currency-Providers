package austeretony.oxygen_cp_sqlite.server.currency;

import austeretony.oxygen_core.common.main.OxygenMain;
import austeretony.oxygen_core.server.currency.CurrencyProvider;
import austeretony.oxygen_core.server.currency.CurrencySource;
import austeretony.oxygen_cp_sqlite.common.config.CurrencyProviderConfig;
import org.sqlite.JDBC;

import javax.annotation.Nullable;
import java.sql.*;
import java.util.UUID;

public class SQLiteCurrencyProvider implements CurrencyProvider {

    private static final String
        COLUMN_USER = "user",
        COLUMN_BALANCE = "balance";

    private final int index;
    private final String tableName, currencyName;

    private boolean tableCreated;

    public SQLiteCurrencyProvider(int index, String tableName, String currencyName) {
        this.index = index;
        this.tableName = tableName;
        this.currencyName = currencyName;

        try {
            DriverManager.registerDriver(new JDBC());
            if (testConnection()) {
                createTableIfNotExist();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Nullable
    private Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC.PREFIX + CurrencyProviderConfig.DATA_BASE_URL.asString());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return connection;
    }

    private boolean testConnection() {
        Connection connection = connect();
        if (connection == null) {
            OxygenMain.logError(1, "[SQLite Currency Provider] #{}/{}: failed to connect to DB during connection test.",
                    index, currencyName);
            return false;
        }

        OxygenMain.logInfo(1, "[SQLite Currency Provider] #{}/{}: successfully connected to DB during connection test.",
                index, currencyName);
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;
    }

    private void createTableIfNotExist() {
        Connection connection = connect();
        if (connection == null) {
            OxygenMain.logError(1, "[SQLite Currency Provider] #{}/{}: failed to connect to DB.",
                    index, currencyName);
            return;
        }

        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER + " TEXT NOT NULL, " +
                COLUMN_BALANCE + " INTEGER SIGNED NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            statement.execute("CREATE UNIQUE INDEX IF NOT EXISTS index_" + COLUMN_USER + "_" + currencyName
                    + " ON " + tableName + "(" + COLUMN_USER + ")");

            OxygenMain.logInfo(1, "[SQLite Currency Provider] #{}/{}: created or checked table {}.",
                    index, currencyName, tableName);
            tableCreated = true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public String getName() {
        return "sqlite_" + currencyName;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public CurrencySource getSource() {
        return CurrencySource.DATA_BASE;
    }

    @Override
    public boolean isForcedSync() {
        return false;
    }

    @Nullable
    @Override
    public Long getBalance(UUID playerUUID, String username) {
        if (!tableCreated) return null;
        Connection connection = connect();
        if (connection == null) {
            OxygenMain.logError(1, "[SQLite Currency Provider] #{}/{}: failed to connect to DB while getting balance: {}",
                    index, currencyName, username);
            return null;
        }

        String sql = "SELECT " + COLUMN_BALANCE + " FROM " + tableName + " WHERE " + COLUMN_USER + " = ?";

        Long balance = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                balance = resultSet.getLong(COLUMN_BALANCE);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return balance;
    }

    @Nullable
    @Override
    public Long setBalance(UUID playerUUID, String username, long value) {
        if (!tableCreated) return null;
        Connection connection = connect();
        if (connection == null) {
            OxygenMain.logError(1, "[SQLite Currency Provider] #{}/{}: failed to connect to DB while updating balance: {}",
                    index, currencyName, username);
            return null;
        }

        String sql = "REPLACE INTO " + tableName + " (" + COLUMN_USER + ", " + COLUMN_BALANCE + ") VALUES (?, ?)";

        Long balance = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setLong(2, value);

            statement.executeUpdate();
            balance = value;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return balance;
    }

    @Nullable
    @Override
    public Long incrementBalance(UUID playerUUID, String username, long increment) {
        if (!tableCreated) return null;
        Connection connection = connect();
        if (connection == null) {
            OxygenMain.logError(1, "[SQLite Currency Provider] #{}/{}: failed to connect to DB while incrementing balance: {}",
                    index, currencyName, username);
            return null;
        }

        String sql = "INSERT INTO " + tableName + "(" + COLUMN_USER + ", " + COLUMN_BALANCE + ") VALUES(?, ?) " +
                "ON CONFLICT(user) DO UPDATE SET " + COLUMN_BALANCE + " = " + COLUMN_BALANCE + " + " + increment + " RETURNING " + COLUMN_BALANCE;

        Long balance = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setLong(2, increment);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                balance = (long) resultSet.getInt(COLUMN_BALANCE);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return balance;
    }

    @Nullable
    @Override
    public Long decrementBalance(UUID playerUUID, String username, long decrement) {
        if (!tableCreated) return null;
        Connection connection = connect();
        if (connection == null) {
            OxygenMain.logError(1, "[SQLite Currency Provider] #{}/{}: failed to connect to DB while decrementing balance: {}",
                    index, currencyName, username);
            return null;
        }

        String sql = "INSERT INTO " + tableName + "(" + COLUMN_USER + ", " + COLUMN_BALANCE + ") VALUES(?, ?) " +
                "ON CONFLICT(user) DO UPDATE SET " + COLUMN_BALANCE + " = " + COLUMN_BALANCE + " - " + decrement + " RETURNING " + COLUMN_BALANCE;

        Long balance = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setLong(2, -decrement);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                balance = (long) resultSet.getInt(COLUMN_BALANCE);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return balance;
    }

    @Override
    public void updated(UUID playerUUID, String username, @Nullable Long balance) {}
}
