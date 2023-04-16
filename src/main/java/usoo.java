
import java.util.ArrayList;
import java.util.List;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class usoo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String archi[] = null;
		List<Persona> Personas=new ArrayList<Persona>();
		List<Partido> Partidos = new ArrayList<Partido>();
		List<Pronostico> pronosticos = new ArrayList<Pronostico>();
		int numRonda = 1;
		int ron=1;
		List<Ronda> rondas = new ArrayList<Ronda>();
		Path ARCHIVO = Paths.get("partidos.txt");
		Path ARCHIVO2 = Paths.get("pronosticos.txt");		
		String div[]=null;
		div=dividir(ARCHIVO,archi);
		int j=0;
		
		while(j<div.length) {
			numRonda= Integer.parseInt(div[j]);	
			String equi1 = div[j+1];
			String equi2 = div[j+2];
			int gol1= Integer.parseInt(div[j+3]);
			int gol2= Integer.parseInt(div[j+4]);
			Equipo equipo1=new Equipo(equi1);
			Equipo equipo2=new Equipo(equi2);
			Partidos.add(new Partido(equipo1,equipo2,gol1,gol2));
			j=j+5;
			if(numRonda!=ron) {
				rondas.add(new Ronda(numRonda-1,Partidos));
				ron++;
				Partidos.clear();
			}
			
		}

		archi=null;
		String div1[]=null;
		div1=dividir(ARCHIVO2,archi);
		j=0;
		List<Partido> pa = new ArrayList<Partido>();
		
		Partido p;
		while(j<div.length) {
			String nom = div1[j];
			int ronda= Integer.parseInt(div1[j+1]);
			int par= Integer.parseInt(div1[j+2]);
			int pron= Integer.parseInt(div1[j+3]);
			pa=rondas.get(ronda-1).getPartidos();
			p=pa.get(par-1);
			
			pronosticos.add(new Pronostico(p,pron));
			
			
			if(!nom.equals(div1[j+4])||div1[j+4].equals(null)) {
				Personas.add(new Persona(nom,pronosticos));
				pronosticos.clear();
			
			}
			j=j+4;
			}
		
		calcularSalida(Personas);
		mostrarFINL(Personas);
		
		}
	
	
	private static void calcularSalida(List<Persona> Personas) {
		for(Persona c:Personas) {
			c.resltados();
			}
			
	}
	private static void mostrarFINL(List<Persona> personas) {
		for(Persona c:personas) {
		System.out.println(c.mostrar());
		
	}
	}

	
	public static String[] dividir (Path aRCHIVO, String[] archiv) {
		try {
			for (String linea: Files.readAllLines(aRCHIVO)) {
				archiv=linea.split(" ");
			}

			} catch (IOException e) {
			e.printStackTrace();
			}
		return archiv;
	}
	
}
