package it.polito.tdp.porto.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.porto.model.Adiacenza;
import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				return autore;
			}
			conn.close();
			return null;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}
			conn.close();
			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Author> getAllAutori() {
			final String sql = "SELECT * FROM author ";
			List<Author> autori = new ArrayList<Author>();
			try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				
				while (rs.next()) {

					Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
					autori.add(autore);
				}
				conn.close();
				return autori;

			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}
		
		
	}

	public void getAllAutori(Map<Integer, Author> idMap) {
		final String sql = "SELECT * FROM author ";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				idMap.put(autore.getId(), autore);
			}

			conn.close();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	
		
	}

	public List<Adiacenza> getAllAdiacenzie(Map<Integer, Author> idMap, Map<Integer,Paper> idPapMap) {
		List<Adiacenza> l=new ArrayList<Adiacenza>();
		final String sql = "SELECT c1.authorid as a1 , c2.authorid as a2 , c1.eprintid as e FROM creator c1, creator c2 WHERE c1.eprintid=c2.eprintid AND c1.authorid>c2.authorid ";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {

				Adiacenza a=new Adiacenza(idMap.get(rs.getInt("a1")),idMap.get(rs.getInt("a2")) ,idPapMap.get(rs.getInt("e")));
				l.add(a);
			}
			conn.close();
			return l;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		
	}

	public void getAllPaper(Map<Integer,Paper> idpmap) {
		final String sql = "SELECT * FROM paper ";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
						// COSTRUTTORE (int eprintid, String title, String issn, String publication, String type, String types)
				Paper paper= new Paper(rs.getInt("eprintid"), rs.getString("title"),rs.getString("issn"), rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				idpmap.put(paper.getEprintid(), paper);
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	
		
	}
}