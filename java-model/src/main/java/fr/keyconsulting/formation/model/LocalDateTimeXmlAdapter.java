package fr.keyconsulting.formation.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeXmlAdapter extends XmlAdapter<String, LocalDateTime> {
	@Override
	public LocalDateTime unmarshal(String v) throws Exception {
		return LocalDateTime.parse(v, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	@Override
	public String marshal(LocalDateTime v) throws Exception {
		return v.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}
}