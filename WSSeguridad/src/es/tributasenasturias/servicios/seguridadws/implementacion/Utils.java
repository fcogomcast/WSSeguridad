package es.tributasenasturias.servicios.seguridadws.implementacion;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class Utils {
	/**
	 * Convierte una cadena (String) a {@link Document}. 
	 * @param xml Xml de cadena a convertir.
	 * @return {@link Document} equivalente al xml
	 * @throws Exception en caso de que falle la conversi�n.
	 */
	public static Document string2Doc(String xml) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		InputSource is = new InputSource( new StringReader( xml ) );
		return builder.parse( is );
		
		
	}
	/**
	 * Convierte un documento de {@link Document} en su representaci�n en cadena.
	 * @param doc Documento a transformar
	 * @return Representaci�n en cadena del documento.
	 * @throws Exception Si se produce un fallo al convertir el documento.
	 */
	public static String doc2String (Document doc) throws Exception
	{
	       DOMSource domSource = new DOMSource(doc);
	       StringWriter writer = new StringWriter();
	       StreamResult result = new StreamResult(writer);
	       TransformerFactory tf = TransformerFactory.newInstance();
	       Transformer transformer = tf.newTransformer();
	       transformer.transform(domSource, result);
	       return writer.toString();
	}
	/**
	 * Devuelve la palabra indicada seg�n el siguiente formato:
	 * 	-Primera letra en may�sculas
	 *  -Siguientes letras en min�sculas.
	 * @param palabra Palabra a la que se aplicar� transformaci�n.
	 * @return La palabra con la primera letra en may�sculas.
	 */
	public static String initcap (String palabra)
	{
		char[] cad = palabra.toLowerCase().toCharArray();
		cad[0] = Character.toUpperCase(cad[0]);
		return new String (cad);
	}
}
