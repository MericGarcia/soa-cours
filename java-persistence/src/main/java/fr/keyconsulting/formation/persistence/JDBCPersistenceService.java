package fr.keyconsulting.formation.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.keyconsulting.formation.model.Calcul;
import fr.keyconsulting.formation.model.Operand;
import fr.keyconsulting.formation.model.Operator;
import fr.keyconsulting.formation.model.Operators;
import fr.keyconsulting.formation.persistence.database.DataBaseHandler;
import fr.keyconsulting.formation.service.PersistenceService;

public class JDBCPersistenceService implements PersistenceService {

	private static final String PASSWORD = "test";
	private static final String USER = "test";
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/testJDBC";

	private DataBaseHandler dbHandler;

	public JDBCPersistenceService() {
		dbHandler = DataBaseHandler.getInstance();
		try {
			dbHandler.init(DRIVER, URL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void persist(Calcul calcul) {
		try {
			dbHandler.connect(USER, PASSWORD);
			insertQuery(calcul);
			dbHandler.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				dbHandler.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void insertQuery(Calcul calcul) throws SQLException {
		Statement stmt = null;
		String lOp = calcul.getLeftOperand().getValue().toString();
		String op = calcul.getOperator().getCode();
		String rOp = calcul.getRightOperand().getValue().toString();
		String time = calcul.getTime().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss"));
		String commentary = calcul.getCommentary();

		String query = "insert into CALCUL " + "(rightOperand,operator,leftOperand,time,commentary) " + "VALUES ('" + lOp + "','"
				+ op + "','" + rOp + "'," + "to_date('" + time + "', 'yyyy-mm-dd hh24:mi:ss'),'"+commentary+"')";

		stmt = dbHandler.getConnection().createStatement();
		
		stmt.executeUpdate(query);
	}

	private List<Calcul> selectAllQuery() throws SQLException {
		List<Calcul> calculs = new ArrayList<>();
		Statement stmt = null;

		String query = "select rightOperand,operator,leftOperand,time,commentary from CALCUL";

		stmt = dbHandler.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Operand leftOperand = new Operand(rs.getBigDecimal("leftOperand"));
			Operator operator = Operators.of(rs.getString("operator"));
			Operand rightOperand = new Operand(rs.getBigDecimal("rightOperand"));
			LocalDateTime time = rs.getTimestamp("time").toLocalDateTime();
			String commentary = rs.getString("commentary");
			calculs.add(new Calcul(leftOperand, operator, rightOperand, time,commentary));
		}
		return calculs;
	}

	@Override
	public List<Calcul> load() {
		List<Calcul> result = Collections.emptyList();
		try {
			dbHandler.connect(USER, PASSWORD);
			dbHandler.getConnection().createStatement();
			result = selectAllQuery();
			dbHandler.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				dbHandler.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
