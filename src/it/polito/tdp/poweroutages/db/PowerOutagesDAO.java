package it.polito.tdp.poweroutages.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import it.polito.tdp.poweroutages.model.Arco;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Outage;

public class PowerOutagesDAO {
	
	public List <Outage> listAllOutagesInKMonths(Map <Integer, Nerc > idMap,Integer K){
		
		String sql = "SELECT id, date_event_began as d1, date_event_finished as d2, nerc_id "
				+ "from PowerOutages "
				+ "where date_event_began<'2003-08-24 18:00:00'";
		
		List <Outage> out = new ArrayList <Outage>();
		
		/*LocalDate ld = LocalDate.of(2000, 1, 1);
		LocalTime lt = LocalTime.of(0, 0);
		LocalDateTime beginning = LocalDateTime.of(ld, lt);
		
		LocalDateTime end = beginning.plus(Duration.of(K, ChronoUnit.MONTHS));*/
		
		try {
			Connection conn = ConnectDB.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			//st.setTimestamp(1, end); ?????????????????????????????????
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Outage o = new Outage (res.getInt("id"),res.getTimestamp("d1").toLocalDateTime(),res.getTimestamp("d2").toLocalDateTime(),idMap.get(res.getInt("nerc_id")));
				out.add(o);
			}//while
			
			conn.close();
			return out ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	

	
	public void loadAllNercs(Map<Integer, Nerc> idMap){
		
		
		String sql = "SELECT id, value FROM nerc";
		
		try {
			Connection conn = ConnectDB.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				if(!idMap.containsKey(res.getInt("id"))) {
					Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
					idMap.put(res.getInt("id"),n );
					//System.out.println(n);
				}//if
				
			}//while
			
			conn.close();
			return  ;

		} catch (SQLException e) {
			e.printStackTrace();
			return  ;
		}

	}


public List <Arco> listArchi(Map <Integer,  Nerc > idMap){
	String sql = "SELECT  nr1.nerc_one as n1, nr2.nerc_two as n2, COUNT(*) as peso "
			+ "from PowerOutages as po1, PowerOutages as po2, NercRelations as nr1, NercRelations as nr2 "
			+ "where po1.id=nr1.nerc_one and po2.id=nr2.nerc_one and nr1.nerc_one<>nr2.nerc_two AND year(po1.date_event_began)=year(po2.date_event_began) and month(po1.date_event_began)=month(po2.date_event_began) "
			+ "group by nr1.nerc_one, nr2.nerc_two, year(po1.date_event_began), month(po1.date_event_began)";

	Connection conn = ConnectDB.getConnection();
	List <Arco> archi = new ArrayList <Arco>();

	try {
		PreparedStatement st = conn.prepareStatement(sql);
		
		ResultSet res = st.executeQuery();
		while (res.next()) {

			Nerc m1 = idMap.get(res.getInt("n1"));
			Nerc m2 = idMap.get(res.getInt("n2"));
			
			if(m1!=null & m2!=null & res.getInt("peso")>0) {
				Arco a = new Arco(m1,m2,res.getInt("peso"));
				archi.add(a);
				//System.out.println(a);
			}
		

		}
		conn.close();
		return archi;
		
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}



}
