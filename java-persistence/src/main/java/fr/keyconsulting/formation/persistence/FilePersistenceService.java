package fr.keyconsulting.formation.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import fr.keyconsulting.formation.model.Calcul;
import fr.keyconsulting.formation.service.PersistenceService;

public class FilePersistenceService implements PersistenceService {
	
	private String FILE_LOCATION = "src/main/resources/fr/keyconsulting/formation/";
	private String FILE_NAME = "calcul.csv";
	
	BufferedReader br;
	BufferedWriter bw;
	
	
	public FilePersistenceService(){
		InputStream is = null;
		OutputStream os = null;
		File csv = new File(FILE_LOCATION + FILE_NAME);
		if (!csv.exists()) {
			try {
				csv.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			is = new FileInputStream(csv);
			os = new FileOutputStream(csv,true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		br = new BufferedReader(new InputStreamReader(is));
		bw = new BufferedWriter(new OutputStreamWriter(os));
	}
	
	public void persist(Calcul calcul){
		StringJoiner sj = new StringJoiner(";");
		sj.add(calcul.getLeftOperand().getValue().toString());
		sj.add(calcul.getOperator().getCode());
		sj.add(calcul.getRightOperand().getValue().toString());
		sj.add(calcul.getTime().format(DateTimeFormatter.ISO_DATE_TIME));
		try {
			bw.write(sj.toString());
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public List<Calcul> load(){
		return br.lines().map( new CsvLineToCalculMapper()).collect(Collectors.toList());
	}

}
