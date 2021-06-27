package it.polito.tdp.poweroutages.db;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TestDAO {

	public static void main(String[] args) {
		
		LocalDate ld = LocalDate.of(2000, 1, 1);
		LocalTime lt = LocalTime.of(10, 0);
		LocalDateTime uno = LocalDateTime.of(ld, lt);
		
		LocalDate ld1 = LocalDate.of(2000, 1, 2);
		LocalTime lt1 = LocalTime.of(8, 0);
		LocalDateTime due = LocalDateTime.of(ld1, lt1);
		
		LocalDateTime end = uno.plus(Duration.of(2, ChronoUnit.HOURS));
		System.out.println("Con durata arrivo a :"+end);
		
		Long bonus = (uno.until(due, ChronoUnit.DAYS));
		System.out.println("Tra uno e due: "+bonus);
		
	}

}
