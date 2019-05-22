package it.polito.tdp.porto.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.graph.*;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	private Graph<Author,Adiacenza> grafo;
	private Map<Integer,Author> idMap;
	private Map<Integer, Paper> idPMap;
	


	public Model() {
		super();
		this.grafo = new SimpleGraph<>(Adiacenza.class);
		idMap=new HashMap<Integer,Author>();
		idPMap=new HashMap<Integer,Paper>();
	}
	
	public void creaGrafo() {
		
		PortoDAO dao = new PortoDAO();
		List<Author> listaAutori=new ArrayList<Author>();
		List<Adiacenza> listAdiacenze = new ArrayList<Adiacenza>();
		//vertici
		dao.getAllAutori(idMap);
		Graphs.addAllVertices(grafo, idMap.values());
		
		//edgi
		dao.getAllPaper(idPMap);
		listAdiacenze=dao.getAllAdiacenzie(idMap,idPMap);
		
		for(Adiacenza a:listAdiacenze) {
			grafo.addEdge(a.getA1(), a.getA2(), a);
			
		}
		System.out.format("il grafo ha %d vertici e %d archi", grafo.vertexSet().size(),grafo.edgeSet().size());
		
	}




	public void getAllPapers() { //carica solo i paper in idmap

		PortoDAO dao = new PortoDAO();
		List<Paper> listaPaperi=new ArrayList<Paper>();
		
		dao.getAllPaper(idPMap);

	}


	public List<Author> getAllAuthors() { 	// ritorna lista

		PortoDAO dao = new PortoDAO();
		List<Author> listaAutori=new ArrayList<Author>();
		
		listaAutori=dao.getAllAutori();
		
		Collections.sort(listaAutori);
		return listaAutori;
	}

	public List<Author> getCoautori(Author autore) {

		return Graphs.neighborListOf(grafo, autore);
			
	}
	public List<Adiacenza> trovaCamminoMinimo(Author arrivo, Author partenza){
		DijkstraShortestPath<Author,Adiacenza> dijkstra= new DijkstraShortestPath<>(this.grafo);
		GraphPath<Author,Adiacenza> path=dijkstra.getPath(partenza, arrivo);
		return path.getEdgeList();
	}

}
