package br.com.nava.demo.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class OptimizedResultSetMapper<T> implements ResultSetExtractor<List<T>> {

    private List<String> resultSetAvailableColumns;

    /**
     * Goes through the ResultSet's metadata and gets all column names in it
     *
     * @param rs ResultSet
     * @return List of column names
     * @throws SQLException Error while processing metadata from the ResultSet
     */
    protected static List<String> getColumnNamesFromResultSet(ResultSet rs) throws SQLException {
        List<String> columnNames = new ArrayList<>();

        ResultSetMetaData metaData = rs.getMetaData();
        int colCount = metaData.getColumnCount();
        for (int i = 1; i <= colCount; i++) {
            columnNames.add(metaData.getColumnLabel(i));
        }

        return columnNames;
    }

    /**
     * Extracts data from the ResultSet
     *
     * @param rs ResultSet
     * @return List of mapped objects
     */
    public List<T> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<T> results = new ArrayList<>();

        if (rs != null) {
            while (rs.next()) {
                results.add(map(rs));
            }
        }

        return results;
    }

    /**
     * Maps the given ResultSet into the appropriate object
     *
     * @param rs ResultSet
     * @return Mapped object
     */
    public abstract T map(ResultSet rs) throws SQLException;

    /**
     * Iterate through the provided ResultSet and resets the available columns names
     *
     * @param rs ResultSet
     * @throws SQLException Error while processing metadata from the ResultSet
     */
    protected void resetResultSetAvailableColumnNames(ResultSet rs) throws SQLException {
        resultSetAvailableColumns = getColumnNamesFromResultSet(rs);
    }

    /**
     * Checks if the column exists in the ResultSet
     *
     * @param rs         ResultSet
     * @param columnName Column name to be checked
     * @return [true] if it exists [false] if it does not exist
     * @throws SQLException Error while processing metadata from the ResultSet
     */
    private boolean columnExists(ResultSet rs, String columnName) throws SQLException {
        if (resultSetAvailableColumns == null) {
            resetResultSetAvailableColumnNames(rs);
        }

        return (this.resultSetAvailableColumns.contains(columnName));
    }

    /**
     * Gets String value from the given ResultSet column
     *
     * @param rs           ResultSet
     * @param columnLabel  Column name
     * @param defaultValue Default value to be returned if null
     * @return String value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected String getString(ResultSet rs, String columnLabel, String defaultValue) throws SQLException {
        if (columnExists(rs, columnLabel)) {
            String value = rs.getString(columnLabel);

            if (!rs.wasNull()) {
                return value;
            }
        }

        return defaultValue;
    }

    /**
     * Gets String value from the given ResultSet column
     *
     * @param rs          ResultSet
     * @param columnLabel Column name
     * @return String Value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected String getString(ResultSet rs, String columnLabel) throws SQLException {
        return getString(rs, columnLabel, null);
    }

    /**
     * Gets Long value from the given ResultSet column
     *
     * @param rs           ResultSet
     * @param columnLabel  Column name
     * @param defaultValue Default value to be returned if null
     * @return Long value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected Long getLong(ResultSet rs, String columnLabel, Long defaultValue) throws SQLException {
        if (columnExists(rs, columnLabel)) {
            Long value = rs.getLong(columnLabel);

            if (!rs.wasNull()) {
                return value;
            }
        }

        return defaultValue;
    }

    /**
     * Gets Long value from the given ResultSet column
     *
     * @param rs          ResultSet
     * @param columnLabel Column name
     * @return Long value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected Long getLong(ResultSet rs, String columnLabel) throws SQLException {
        return getLong(rs, columnLabel, null);
    }

    /**
     * Gets Integer value from the given ResultSet column
     *
     * @param rs           ResultSet
     * @param columnLabel  Column name
     * @param defaultValue Default value to be returned if null
     * @return Integer value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected Integer getInteger(ResultSet rs, String columnLabel, Integer defaultValue) throws SQLException {
        if (columnExists(rs, columnLabel)) {
            Integer value = rs.getInt(columnLabel);

            if (!rs.wasNull()) {
                return value;
            }
        }

        return defaultValue;
    }

    /**
     * Gets Integer value from the given ResultSet column
     *
     * @param rs          ResultSet
     * @param columnLabel Column name
     * @return Integer value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected Integer getInteger(ResultSet rs, String columnLabel) throws SQLException {
        return getInteger(rs, columnLabel, null);
    }

    /**
     * Gets Double value from the given ResultSet column
     *
     * @param rs           ResultSet
     * @param columnLabel  Column name
     * @param defaultValue Default value to be returned if null
     * @return Double value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected Double getDouble(ResultSet rs, String columnLabel, Double defaultValue) throws SQLException {
        if (columnExists(rs, columnLabel)) {
            Double value = rs.getDouble(columnLabel);

            if (!rs.wasNull()) {
                return value;
            }
        }

        return defaultValue;
    }

    /**
     * Gets Double value from the given ResultSet column
     *
     * @param rs          ResultSet
     * @param columnLabel Column name
     * @return Double value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected Double getDouble(ResultSet rs, String columnLabel) throws SQLException {
        return getDouble(rs, columnLabel, null);
    }

    /**
     * Gets Boolean value from the given ResultSet column
     *
     * @param rs           ResultSet
     * @param columnLabel  Column name
     * @param defaultValue Default value to be returned if null
     * @return Boolean value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected Boolean getBoolean(ResultSet rs, String columnLabel, Boolean defaultValue) throws SQLException {
        if (columnExists(rs, columnLabel)) {
            Boolean value = rs.getBoolean(columnLabel);

            if (!rs.wasNull()) {
                return value;
            }
        }

        return defaultValue;
    }

    /**
     * Gets Boolean value from the given ResultSet column
     *
     * @param rs          ResultSet
     * @param columnLabel Column name
     * @return Boolean value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected Boolean getBoolean(ResultSet rs, String columnLabel) throws SQLException {
        return getBoolean(rs, columnLabel, null);
    }

    /**
     * Gets Timestamp value from the given ResultSet column
     *
     * @param rs           ResultSet
     * @param columnLabel  Column name
     * @param defaultValue Default value to be returned if null
     * @return Timestamp value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected Timestamp getTimestamp(ResultSet rs, String columnLabel, Timestamp defaultValue) throws SQLException {
        if (columnExists(rs, columnLabel)) {
            Timestamp value = rs.getTimestamp(columnLabel);

            if (!rs.wasNull()) {
                return value;
            }
        }

        return defaultValue;
    }

    /**
     * Gets Timestamp value from the given ResultSet column
     *
     * @param rs          ResultSet
     * @param columnLabel Column name
     * @return Timestamp value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected Timestamp getTimestamp(ResultSet rs, String columnLabel) throws SQLException {
        return getTimestamp(rs, columnLabel, null);
    }

    /**
     * Gets LocalDateTime value from the given ResultSet column
     *
     * @param rs          ResultSet
     * @param columnLabel Column name
     * @return LocalDateTime value
     * @throws SQLException Error while parsing the ResultSet
     */
    protected LocalDateTime getLocalDateTime(ResultSet rs, String columnLabel) throws SQLException {
        Timestamp ts = getTimestamp(rs, columnLabel);

        if (ts != null) {
            return ts.toLocalDateTime();
        }

        return null;
    }
}
