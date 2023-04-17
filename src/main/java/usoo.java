
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
		
		int ron=1;
		List<Ronda> rondas = new ArrayList<Ronda>();
		Path ARCHIVO = Paths.get("partidos.txt");
		Path ARCHIVO2 = Paths.get("pronosticos.txt");		
		String div[]=null;
		div=dividir(ARCHIVO,archi);
		int j=0;
		
		while(j<div.length) {
		int	numRonda= Integer.parseInt(div[j]);	
			String equi1 = div[j+1];
			String equi2 = div[j+2];
			int gol1= Integer.parseInt(div[j+3]);
			int gol2= Integer.parseInt(div[j+4]);
			Equipo equipo1=new Equipo(equi1);
			Equipo equipo2=new Equipo(equi2);
			Partidos.add(new Partido(equipo1,equipo2,gol1,gol2));
			if(numRonda!=ron||j+6>div.length) {
				rondas.add(new Ronda(numRonda-1,Partidos));
				ron++;
				Partidos.clear();
			}
			j=j+5;	
			
		
		}
		archi=null;
		div=null;
		div=dividir(ARCHIVO2,archi);
		j=0;
		Partido p;
		String con=div[j+4];
		while(j<div.length) {
			String nom = div[j];
			int ronda= Integer.parseInt(div[j+1]);
			int par= Integer.parseInt(div[j+2]);
			int pron= Integer.parseInt(div[j+3]);
			List<Partido> pa = rondas.get(ronda-1).partidos;
			p=pa.get(par-1);
			pronosticos.add(new Pronostico(p,pron));	
			System.out.print("HOLAA ");
			if(!nom.equals(con)) {
				Personas.add(new Persona(nom,pronosticos));
				con=div[j+4];
				pronosticos.clear();
				System.out.print("EXITO ");
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
