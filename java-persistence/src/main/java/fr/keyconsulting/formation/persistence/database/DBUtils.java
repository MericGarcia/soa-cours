package fr.keyconsulting.formation.persistence.database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import fr.keyconsulting.formation.model.Attribute;
import fr.keyconsulting.formation.model.Operand;
import fr.keyconsulting.formation.model.Operator;
import fr.keyconsulting.formation.model.Operators;

public class DBUtils {

	public static List<Field> getFields(Object bean){
		List<Field> fields = new ArrayList<>();
		for(Field field : bean.getClass().getDeclaredFields()){
			if(field.isAnnotationPresent(Attribute.class)){
				fields.add(field);
			}
		}
		return fields;
	}

	/**
	 * Populates simple properties of a bean reading a ResultSet according to
	 * the bean mapping.
	 */
	public static void populateObject(Object bean, ResultSet rs) throws SQLException {
		List<Field> fields = getFields(bean);
		for (Field field : fields) {
			setProperty(bean, rs, field.getName());
		}
	}

	public static void setColunmValue(PreparedStatement statement, Object bean, String propertyName, int column)
			throws SQLException {
		Class<?> type = getPropertyType(bean, propertyName);
		if (type == Boolean.TYPE) {
			Boolean value = (Boolean) getBeanProperty(bean, propertyName);
			statement.setBoolean(column, value);
		} else if (type == String.class) {
			String value = (String) getBeanProperty(bean, propertyName);
			statement.setString(column, value);
		} else if (type == Integer.TYPE) {
			Integer value = (Integer) getBeanProperty(bean, propertyName);
			statement.setInt(column, value);
		} else if (java.util.Date.class.isAssignableFrom(type)) {
			java.util.Date value = (Date) getBeanProperty(bean, propertyName);
			statement.setDate(column, new java.sql.Date(value.getTime()));
		} else if (java.time.LocalDate.class.isAssignableFrom(type)) {
			java.time.LocalDate value = (LocalDate) getBeanProperty(bean, propertyName);
			statement.setDate(column, java.sql.Date.valueOf(value));
		} else if (java.time.LocalDateTime.class.isAssignableFrom(type)) {
			java.time.LocalDateTime value = (LocalDateTime) getBeanProperty(bean, propertyName);
			statement.setTimestamp(column, java.sql.Timestamp.valueOf(value));
		} else if (type == Double.TYPE) {
			Double value = (Double) getBeanProperty(bean, propertyName);
			statement.setDouble(column, value);
		} else if (type == Long.TYPE) {
			Long value = (Long) getBeanProperty(bean, propertyName);
			statement.setLong(column, value);
		} else if (type == Float.TYPE) {
			Float value = (Float) getBeanProperty(bean, propertyName);
			statement.setFloat(column, value);
		}else if (Operand.class.isAssignableFrom(type)) {
			BigDecimal value = ((Operand) getBeanProperty(bean, propertyName)).getValue();
			statement.setBigDecimal(column, value);
		}else if (Operator.class.isAssignableFrom(type)) {
			String value = ((Operator) getBeanProperty(bean, propertyName)).getCode();
			statement.setString(column, value);
		} else
			throw new RuntimeException("Type not supported: " + type);
	}

	/**
	 * Sets a Bean value read from a ResultSet according to the PropertyMapping
	 */
	private static void setProperty(Object bean, ResultSet rs, String propertyName) throws SQLException {
		final String colName = propertyName;
		Class<?> type = getPropertyType(bean, propertyName);
		Object value;
		if (type == Boolean.TYPE)
			value = new Boolean(rs.getBoolean(colName));
		else if (type == String.class)
			value = rs.getString(colName);
		else if (type == Integer.TYPE)
			value = new Integer(rs.getInt(colName));
		else if (java.sql.Date.class.isAssignableFrom(type))
			value = rs.getDate(colName);
		else if (java.time.LocalDate.class.isAssignableFrom(type))
			value = rs.getDate(colName).toLocalDate();
		else if (java.time.LocalDateTime.class.isAssignableFrom(type))
			value = rs.getTimestamp(colName).toLocalDateTime();
		else if (type == Double.TYPE)
			value = new Double(rs.getDouble(colName));
		else if (type == Float.TYPE)
			value = new Float(rs.getDouble(colName));
		else if (type == Long.TYPE)
			value = new Long(rs.getLong(colName));
		else if (Operator.class.isAssignableFrom(type))
			value = Operators.of(rs.getString(colName));
		else if (Operand.class.isAssignableFrom(type))
			value = new Operand(rs.getBigDecimal(colName));
		else
			throw new RuntimeException("Type not supported: " + type);
		setBeanProperty(bean, propertyName, value);
	}

	private static Class<?> getPropertyType(Object bean, String propertyName) {
		Class<?> type = Object.class;
		try {
			type = PropertyUtils.getPropertyType(bean, propertyName);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return type;
	}

	/**
	 * Sets a property value for a specified Bean. Uses the Apache Commons
	 * PropertyUtils class.
	 */
	private static void setBeanProperty(Object bean, String name, Object value) {
		try {
			PropertyUtils.setProperty(bean, name, value);
		} catch (Throwable t) {
			throw new RuntimeException("Error while setting property '" + name + "' on " + bean.getClass(), t);
		}
	}

	/**
	 * Sets a property value for a specified Bean. Uses the Apache Commons
	 * PropertyUtils class.
	 */
	private static Object getBeanProperty(Object bean, String name) {
		try {
			return PropertyUtils.getProperty(bean, name);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException("Error while getting property '" + name + "' on " + bean.getClass(), e);
		}
	}

}
