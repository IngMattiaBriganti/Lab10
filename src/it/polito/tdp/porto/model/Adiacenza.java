package it.polito.tdp.porto.model;

import org.jgrapht.graph.DefaultEdge;

public class Adiacenza extends DefaultEdge{
	Author a1;
	Author a2;
	Paper p;
	public Adiacenza(Author a1, Author a2, Paper p) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.p = p;
	}
	public Author getA1() {
		return a1;
	}
	public void setA1(Author a1) {
		this.a1 = a1;
	}
	public Author getA2() {
		return a2;
	}
	public void setA2(Author a2) {
		this.a2 = a2;
	}
	public Paper getP() {
		return p;
	}
	public void setP(Paper p) {
		this.p = p;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adiacenza other = (Adiacenza) obj;
		if (a1 == null) {
			if (other.a1 != null)
				return false;
		} else if (!a1.equals(other.a1))
			return false;
		if (a2 == null) {
			if (other.a2 != null)
				return false;
		} else if (!a2.equals(other.a2))
			return false;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PAPER: " + p +" Autore 1:" + a1 + ", Autore 2: " + a2 + "\n";
	}
	
	

}
