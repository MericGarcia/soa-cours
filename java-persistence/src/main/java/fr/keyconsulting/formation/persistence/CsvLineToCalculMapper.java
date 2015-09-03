package fr.keyconsulting.formation.persistence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import fr.keyconsulting.formation.model.Calcul;
import fr.keyconsulting.formation.model.Operand;
import fr.keyconsulting.formation.model.Operator;
import fr.keyconsulting.formation.model.Operators;

public class CsvLineToCalculMapper implements Function<String,Calcul> {

	@Override
	public Calcul apply(String t) {
		String[] part = t.split(";");
		Operand lOp = new Operand(part[0]);
		Operator op = Operators.of((part[1]));
		Operand rOp = new Operand(part[2]);
		LocalDateTime time = LocalDateTime.parse(part[3],DateTimeFormatter.ISO_DATE_TIME);
		Calcul calcul = new Calcul(lOp,op,rOp,time);
		return calcul;
	}

}
