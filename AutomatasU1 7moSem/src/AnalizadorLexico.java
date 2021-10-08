 /*  				ITSA
 *
 *  Analizador Léxico para el lenguaje java
 *
 * 
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorLexico 
{
	public static int CtaLineas = 0;
	
	public static String LeerArchivo() throws IOException
	{
		File archivo = new File ("C:\\Users\\jorku\\eclipse-workspace\\AutomatasU1 7moSem\\src\\PRUEBA.txt");

      	FileReader fr = new FileReader (archivo);
      	BufferedReader br = new BufferedReader(fr);

      	String Cadena = "";
        String linea = "";
         	
        while((linea = br.readLine())!=null)
        {
        	Cadena += linea + " ";
        	CtaLineas++;
        }
      
      	fr.close();   
      		
      	return Cadena;
	}
	
	public static void Analizar(String Cadena)
	{
		StringTokenizer Tokens = new StringTokenizer(Cadena);
		
		int i=0;
      	
        while(Tokens.hasMoreTokens())
        {
            ExpRegulares(Tokens.nextToken());
            
            i++;
        }
	}
	
	public static void ExpRegulares(String Token)
	{
		Pattern PPalabraReservadaCiclo = Pattern.compile("for|while|do");
		Pattern PPalabraReservadaDesicion = Pattern.compile("switch|case|break|if|else");
		Pattern PReservada = Pattern.compile("import|class|public|static|void|main|return|private|abstract|assert|package|length|out|System|this|print|println");
		Pattern TipoDato = Pattern.compile("int|short|byte|long|float|double|char|String|boolean");
		Pattern Asignacion = Pattern.compile("\\=");
		Pattern Terminador = Pattern.compile("\\;");
		Pattern DosPuntos = Pattern.compile("\\:");

		Pattern LLaveIni = Pattern.compile("\\{");
		Pattern LLaveFin = Pattern.compile("\\}");
		Pattern PIdentificador = Pattern.compile("([a-zA-Z|_]+([0-9]|[a-zA-Z]|_)*)+");   
		Pattern PNumeros = Pattern.compile("[0-9]*");  
		Pattern PNumerosDeci = Pattern.compile("[0-9]+[.][0-9]*|[0-9]*[.][0-9]+");  
		Pattern PMenorQue = Pattern.compile("\\<");  
		Pattern PMayorQue = Pattern.compile("\\>");  
		Pattern PCorcheIni = Pattern.compile("\\[");
		Pattern PCorcheFin = Pattern.compile("\\]");
		Pattern PParentesisIni = Pattern.compile("\\(");
		Pattern PParentesisFin = Pattern.compile("\\)");
		
		Pattern suma = Pattern.compile("\\+");

		Pattern resta = Pattern.compile("\\-");

		Pattern multiplicacion = Pattern.compile("\\*");

		Pattern division = Pattern.compile("\\/");

//		Pattern comillas = Pattern.compile("\\"");
		
		Pattern comillasola = Pattern.compile("\\'");

		Pattern punto = Pattern.compile("\\.");

		Pattern coma = Pattern.compile("\\,");

		Pattern PReservadaExtensorClase = Pattern.compile("extends");

		Pattern PMenorQueIgual = Pattern.compile("[=]+[<]+");  
		Pattern PMayorQueIgual = Pattern.compile("[=]+[>]+");
		Pattern Pmasmas = Pattern.compile("[+]+[+]+");  
		Pattern Pmenosmenos = Pattern.compile("[-]+[-]+");
		
		Matcher MatPR = PReservada.matcher(Token);
		Matcher YaveIni = LLaveIni.matcher(Token);
		Matcher YaveFin = LLaveFin.matcher(Token);
		Matcher MatchTermPC = Terminador.matcher(Token);
		Matcher MatchDosPuntos = DosPuntos.matcher(Token);

		Matcher VarIdent = PIdentificador.matcher(Token);
		Matcher TDato = TipoDato.matcher(Token);
		Matcher OperAsig = Asignacion.matcher(Token);
		Matcher MatchNume = PNumeros.matcher(Token);
		Matcher MatchNumeDec = PNumerosDeci.matcher(Token);
		Matcher MatchMenorQue = PMenorQue.matcher(Token);
		Matcher MatchMayorQue = PMayorQue.matcher(Token);
		
		Matcher MatchMenorQueIgual = PMenorQueIgual.matcher(Token);
		Matcher MatchMayorQueIgual = PMayorQueIgual.matcher(Token);
		Matcher Matchmasmas = Pmasmas.matcher(Token);
		Matcher Matchmenosmenos = Pmenosmenos.matcher(Token);
		
		Matcher MatchCorcheIni = PCorcheIni.matcher(Token);
		Matcher MatchCorcheFin = PCorcheFin.matcher(Token);
		Matcher MatchParentesisIni = PParentesisIni.matcher(Token);
		Matcher MatchParentesisFin = PParentesisFin.matcher(Token);
		Matcher MatchPalabraReservadaCiclo = PPalabraReservadaCiclo.matcher(Token);
		Matcher MatchPalabraReservadaDesicion = PPalabraReservadaDesicion.matcher(Token);
		
		Matcher Matsuma = suma.matcher(Token);
		Matcher Matresta = resta.matcher(Token);
		Matcher Matmult = multiplicacion.matcher(Token);
		Matcher Matdivision = division.matcher(Token);
		//Matcher Matcomi = comillas.matcher(Token);
	    Matcher Matcomisol = comillasola.matcher(Token);
		Matcher Matpunto = punto.matcher(Token);
		Matcher Matcoma = coma.matcher(Token);
		Matcher MatPRExtensClas = PReservadaExtensorClase.matcher(Token);

		
		if(MatPR.find())
			System.out.println(MatPR.group() + " <Palabra Reservada, " + MatPR.group().toUpperCase() + ">");
			  else if(YaveIni.find())
				System.out.println(YaveIni.group() + " <LLave de Inicio, " + YaveIni.group().toUpperCase() + ">");
			  else if(YaveFin.find())
				System.out.println(YaveFin.group() + " <LLave de cierre, " + YaveFin.group().toUpperCase() + ">");
			  else if(MatchTermPC.find())
				  System.out.println(MatchTermPC.group() + " <Punto y coma, " + MatchTermPC.group().toUpperCase() + ">");
			  else if(MatchDosPuntos.find())
				  System.out.println(MatchDosPuntos.group() + " <Dos Puntos, " + MatchDosPuntos.group().toUpperCase() + ">");
			  else if(Matpunto.find())
				  System.out.println(Matpunto.group() + " <Punto, " + Matpunto.group().toUpperCase() + ">");
			  else if(Matcoma.find())
				  System.out.println(Matcoma.group() + " <Coma, " + Matcoma.group().toUpperCase() + ">");
			  else if(TDato.find())
				  System.out.println(TDato.group() + " <Tipo de Dato, " + TDato.group().toUpperCase() + ">");
			  else if(MatchPalabraReservadaCiclo.find())
				  	System.out.println(MatchPalabraReservadaCiclo.group() + " <Palabra Reservada Ciclo, " + MatchPalabraReservadaCiclo.group().toUpperCase() + ">");
			  else if(MatchPalabraReservadaDesicion.find())
				  	System.out.println(MatchPalabraReservadaDesicion.group() + " <Palabra Reservada Desicion, " + MatchPalabraReservadaDesicion.group().toUpperCase() + ">");
			  else if(MatPRExtensClas.find())
				  System.out.println(MatPRExtensClas.group() + " <Palabra Reservada de Extensor de Clase, " + MatPRExtensClas.group().toUpperCase() + ">");
			  else if(VarIdent.find())
				  System.out.println(VarIdent.group() + " <Identificador, " + VarIdent.group().toUpperCase() + ">");
			  else if(MatchMenorQueIgual.find())
				  	System.out.println(MatchMenorQueIgual.group() + " <Simbolo Menor/Igual Que, " + MatchMenorQueIgual.group().toUpperCase() + ">");
			  else if(MatchMayorQueIgual.find())
				  	System.out.println(MatchMayorQueIgual.group() + " <Simbolo Mayor/Igual Que, " + MatchMayorQueIgual.group().toUpperCase() + ">");
			  else if(Matchmasmas.find())
				  	System.out.println(Matchmasmas.group() + " <Simbolo de aumento, " + Matchmasmas.group().toUpperCase() + ">");
			  else if(Matchmenosmenos.find())
				  	System.out.println(Matchmenosmenos.group() + " <Simbolo de disminucion, " + Matchmenosmenos.group().toUpperCase() + ">");
			  else if(OperAsig.find())
				  System.out.println(OperAsig.group() + " <Operando de Asigancion, " + OperAsig.group().toUpperCase() + ">");
			  else if(Matsuma.find())
				  System.out.println(Matsuma.group() + " <Operando de Suma, " + Matsuma.group().toUpperCase() + ">");
		
			  else if(Matresta.find())
				  System.out.println(Matresta.group() + " <Operando de Resta, " + Matresta.group().toUpperCase() + ">");
			  else if(Matmult.find())
					  System.out.println(Matmult.group() + " <Operando de Multiplicacion, " + Matmult.group().toUpperCase() + ">");
			  else if(Matdivision.find())
						  System.out.println(Matdivision.group() + " <Operando de Division, " + Matdivision.group().toUpperCase() + ">");
		
			  else if(Matcomisol.find())
				  System.out.println(Matcomisol.group() + " <Comilla Sola, " + Matcomisol.group().toUpperCase() + ">");
			  else if(MatchNumeDec.find())
				  System.out.println(MatchNumeDec.group() + " <Numero Decimal, " + MatchNumeDec.group().toUpperCase() + ">");
		
		
			  else if(MatchMenorQue.find())
				  	System.out.println(MatchMenorQue.group() + " <Simbolo Menor Que, " + MatchMenorQue.group().toUpperCase() + ">");
			  else if(MatchMayorQue.find())
				  	System.out.println(MatchMayorQue.group() + " <Simbolo Mayor Que, " + MatchMayorQue.group().toUpperCase() + ">");
			  else if(MatchCorcheIni.find())
				  	System.out.println(MatchCorcheIni.group() + " <Corchete De Inicio, " + MatchCorcheIni.group().toUpperCase() + ">");
			  else if(MatchCorcheFin.find())
				  	System.out.println(MatchCorcheFin.group() + " <Corchete De Cierre, " + MatchCorcheFin.group().toUpperCase() + ">");
			  else if(MatchParentesisIni.find())
				  	System.out.println(MatchParentesisIni.group() + " <Parentesis De Inicio, " + MatchParentesisIni.group().toUpperCase() + ">");
			  else if(MatchParentesisFin.find())
				  	System.out.println(MatchParentesisFin.group() + " <Parentesis De Cierre, " + MatchParentesisFin.group().toUpperCase() + ">");
			  else if(MatchNume.find())
				  System.out.println(MatchNume.group() + " <Numero, " + MatchNume.group().toUpperCase() + ">");
		
		
	  }
	
	public static void main (String[] args) throws IOException
	{
		String Cadena = LeerArchivo();
		System.out.print("Numero de lineas: " + CtaLineas + "\n\n");
		
		Analizar(Cadena);
	}
}

