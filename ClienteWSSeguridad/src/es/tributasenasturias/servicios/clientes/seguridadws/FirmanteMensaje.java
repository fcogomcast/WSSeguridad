/**
 * 
 */
package es.tributasenasturias.servicios.clientes.seguridadws;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**Firmante del mensaje
 * @author crubencvs
 *
 */
public class FirmanteMensaje {
	private static final String xmlError="<firma><esError></esError><error></error></firma>";
	/**
	 * Devuelve un XML de error para devolver cuando no se puede
	 * firmar un mensaje.
	 * @param errorMessage Mensaje de error
	 * @return La cadena montada en formato {@link Node}, o null si no se puede montar.
	 */
	private static Node setError (String errorMessage)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			InputSource is = new InputSource( new StringReader( xmlError ) );
			Document doc = builder.parse(is);
			//Ahora, modificamos los valores.
			NodeList nodos = doc.getElementsByTagName("esError");
			if (nodos.getLength()>0)
			{
				Node esError =nodos.item(0);
				esError.appendChild(doc.createTextNode("true"));
				nodos = doc.getElementsByTagName ("mensaje");
				if (nodos.getLength()>0)
				{
					Node error = nodos.item(0);
					error.appendChild(doc.createTextNode(errorMessage));
				}
			}
			return doc.getDocumentElement();
					
		} catch (ParserConfigurationException e) {
			return null;
		} catch (SAXException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
	}
	public static Node selectSingleNode (Document doc, String path)
	{
		NodeList list;
		Node objetivo;
		try
		{
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression exp = xpath.compile(path);
			list = (NodeList)exp.evaluate (doc, XPathConstants.NODESET);
			if (list.getLength()>0)
			{
				objetivo=list.item(0);//El primero
			}
			else
			{
				objetivo=null;
			}
		}
		catch (XPathExpressionException ex)
		{
			objetivo=null;
		}
		return objetivo;
	}
	/**
	 * Llamada a la firma de mensaje, con el mensaje indicado y al endpoint que se le dice.
	 * @param mensaje Mensaje a firmar
	 * @param endpoint Endpoint del servicio de firma de WS Security, normalmente /WSInternos/ProxyServices/PXSeguridadWS
	 * @return Mensaje firmado o un xml de error producido.
	 */
	public static XmlObject firmaMensaje (String mensaje, String aliasCertificado,String endpoint)
	{
		XmlObject respuesta=null;
		//Datos del WSDL
		QName servicio = new QName("http://www.tributasenasturias.es/SeguridadWS/","Seguridad");
		QName port = new QName("http://www.tributasenasturias.es/SeguridadWS/","SeguridadSOAP");
		//Creamos el servicio y apuntamos al puerto
		Service service = Service.create(servicio);
		service.addPort(port, SOAPBinding.SOAP11HTTP_BINDING, endpoint);
		//Despachador de mensajes al servicio.
		Dispatch<SOAPMessage> dispatch = service.createDispatch(port, 
		SOAPMessage.class, Service.Mode.MESSAGE); //Enviamos el mensaje completo.
		
		try {
			MessageFactory mf = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
			//Creamos el mensaje de salida.
			SOAPMessage request = mf.createMessage();
			SOAPBody body = request.getSOAPBody();
			SOAPElement operacion = body.addChildElement(new QName("http://www.tributasenasturias.es/SeguridadWS/","firmarMensaje"));
			SOAPElement nodoMensaje = operacion.addChildElement("mensaje");
			nodoMensaje.addTextNode(mensaje);
			nodoMensaje = operacion.addChildElement("aliasCertificado");
			nodoMensaje.addTextNode(aliasCertificado);
			request.saveChanges();//Aplicamos los cambios
			//Env�o para firmar.
			SOAPMessage response=dispatch.invoke(request);
			//Si ha llegado aqu�, no ha habido excepci�n, 
			//devolvemos el mensaje firmado.
			//Extraemos el nodo "mensajeFirmado"
			Node mensajeFirmado=selectSingleNode(response.getSOAPBody().getOwnerDocument(),"//*[local-name()='mensajeFirmado']/text()");
			respuesta=XmlObject.Factory.parse(mensajeFirmado.getNodeValue());
		} catch (SOAPException e) {
			try {
				respuesta = XmlObject.Factory.parse(setError(e.getMessage()));
				
			} catch (XmlException e1) {
				//Poco podemos hacer
				return null;
			}
		} catch (XmlException e) {
			try {
				respuesta = XmlObject.Factory.parse(setError(e.getMessage()));
				
			} catch (XmlException e1) {
				//Poco podemos hacer
				return null;
			}
		}
		return respuesta;
	}
	
	//CRUBENCVS 07/10/2022
	/**
	 * Llamada a la firma de mensaje con timestamp, con el mensaje indicado, el alias de certificado, el intervalo en segundos de la expiraci�n y al endpoint que se le dice.
	 * @param mensaje Mensaje a firmar
	 * @param aliasCertificado
	 * @param intervaloSegundosExpiracion
	 * @param endpoint Endpoint del servicio de firma de WS Security, normalmente /WSInternos/ProxyServices/PXSeguridadWS
	 * @return Mensaje firmado o un xml de error producido.
	 */
	public static XmlObject firmaMensajeTimestamp (String mensaje, String aliasCertificado,String intervaloSegundosExpiracion, String endpoint)
	{
		XmlObject respuesta=null;
		//Datos del WSDL
		QName servicio = new QName("http://www.tributasenasturias.es/SeguridadWS/","Seguridad");
		QName port = new QName("http://www.tributasenasturias.es/SeguridadWS/","SeguridadSOAP");
		//Creamos el servicio y apuntamos al puerto
		Service service = Service.create(servicio);
		service.addPort(port, SOAPBinding.SOAP11HTTP_BINDING, endpoint);
		//Despachador de mensajes al servicio.
		Dispatch<SOAPMessage> dispatch = service.createDispatch(port, 
		SOAPMessage.class, Service.Mode.MESSAGE); //Enviamos el mensaje completo.
		
		try {
			MessageFactory mf = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
			//Creamos el mensaje de salida.
			SOAPMessage request = mf.createMessage();
			SOAPBody body = request.getSOAPBody();
			SOAPElement operacion = body.addChildElement(new QName("http://www.tributasenasturias.es/SeguridadWS/","firmarMensajeConTimestamp"));
			SOAPElement nodoMensaje = operacion.addChildElement("mensaje");
			nodoMensaje.addTextNode(mensaje);
			nodoMensaje = operacion.addChildElement("aliasCertificado");
			nodoMensaje.addTextNode(aliasCertificado);
			nodoMensaje = operacion.addChildElement("intervaloSegundosExpiracion");
			nodoMensaje.addTextNode(intervaloSegundosExpiracion);
			request.saveChanges();//Aplicamos los cambios
			//Env�o para firmar.
			SOAPMessage response=dispatch.invoke(request);
			//Si ha llegado aqu�, no ha habido excepci�n, 
			//devolvemos el mensaje firmado.
			//Extraemos el nodo "mensajeFirmado"
			Node mensajeFirmado=selectSingleNode(response.getSOAPBody().getOwnerDocument(),"//*[local-name()='mensajeFirmado']/text()");
			respuesta=XmlObject.Factory.parse(mensajeFirmado.getNodeValue());
		} catch (SOAPException e) {
			try {
				respuesta = XmlObject.Factory.parse(setError(e.getMessage()));
				
			} catch (XmlException e1) {
				//Poco podemos hacer
				return null;
			}
		} catch (XmlException e) {
			try {
				respuesta = XmlObject.Factory.parse(setError(e.getMessage()));
				
			} catch (XmlException e1) {
				//Poco podemos hacer
				return null;
			}
		}
		return respuesta;
	}
	
	//CRUBENCVS 47084 20/02/2023
	/**
	 * Llamada a la firma de mensaje, con el mensaje indicado, las URIs de algoritmos de firma y digest y al endpoint que se le dice.
	 * @param mensaje Mensaje a firmar
	 * @param aliasCertificado Alias del certificado de firma
	 * @param uriAlgoritmoFirma URI del algoritmo de firma seg�n XML Dsig
	 * @param uriAlgoritmoDigest URI del algoritmo de resument seg�n XML Dsig
	 * @param endpoint Endpoint del servicio de firma de WS Security, normalmente /WSInternos/ProxyServices/PXSeguridadWS
	 * @return Mensaje firmado o un xml de error producido.
	 */
	public static XmlObject firmaMensajeAlgoritmo (String mensaje, String aliasCertificado,String uriAlgoritmoFirma, String uriAlgoritmoDigest,String endpoint)
	{
		XmlObject respuesta=null;
		//Datos del WSDL
		QName servicio = new QName("http://www.tributasenasturias.es/SeguridadWS/","Seguridad");
		QName port = new QName("http://www.tributasenasturias.es/SeguridadWS/","SeguridadSOAP");
		//Creamos el servicio y apuntamos al puerto
		Service service = Service.create(servicio);
		service.addPort(port, SOAPBinding.SOAP11HTTP_BINDING, endpoint);
		//Despachador de mensajes al servicio.
		Dispatch<SOAPMessage> dispatch = service.createDispatch(port, 
		SOAPMessage.class, Service.Mode.MESSAGE); //Enviamos el mensaje completo.
		
		try {
			MessageFactory mf = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
			//Creamos el mensaje de salida.
			SOAPMessage request = mf.createMessage();
			SOAPBody body = request.getSOAPBody();
			SOAPElement operacion = body.addChildElement(new QName("http://www.tributasenasturias.es/SeguridadWS/","firmarMensajeAlgoritmo"));
			SOAPElement nodoMensaje = operacion.addChildElement("mensaje");
			nodoMensaje.addTextNode(mensaje);
			nodoMensaje = operacion.addChildElement("aliasCertificado");
			nodoMensaje.addTextNode(aliasCertificado);
			nodoMensaje = operacion.addChildElement("uriAlgoritmoFirma");
			nodoMensaje.addTextNode(uriAlgoritmoFirma);
			nodoMensaje = operacion.addChildElement("uriAlgoritmoDigest");
			nodoMensaje.addTextNode(uriAlgoritmoDigest);
			request.saveChanges();//Aplicamos los cambios
			//Env�o para firmar.
			SOAPMessage response=dispatch.invoke(request);
			//Si ha llegado aqu�, no ha habido excepci�n, 
			//devolvemos el mensaje firmado.
			//Extraemos el nodo "mensajeFirmado"
			Node mensajeFirmado=selectSingleNode(response.getSOAPBody().getOwnerDocument(),"//*[local-name()='mensajeFirmado']/text()");
			respuesta=XmlObject.Factory.parse(mensajeFirmado.getNodeValue());
		} catch (SOAPException e) {
			try {
				respuesta = XmlObject.Factory.parse(setError(e.getMessage()));
				
			} catch (XmlException e1) {
				//Poco podemos hacer
				return null;
			}
		} catch (XmlException e) {
			try {
				respuesta = XmlObject.Factory.parse(setError(e.getMessage()));
				
			} catch (XmlException e1) {
				//Poco podemos hacer
				return null;
			}
		}
		return respuesta;
	}
	
	/**
	 * Llamada a la firma de mensaje con timestamp, con el mensaje indicado, el alias de certificado, el intervalo en segundos de la expiraci�n, las URIs de algoritmos a aplicar y al endpoint que se le dice.
	 * @param mensaje Mensaje a firmar
	 * @param aliasCertificado
	 * @param uriAlgoritmoFirma uri del algoritmo de firma a aplicar, seg�n XML Dsig
	 * @param uriAlgoritmoDigest URI  del algoritmo de resument a aplicar, seg�n XML Dsig
	 * @param intervaloSegundosExpiracion
	 * @param endpoint Endpoint del servicio de firma de WS Security, normalmente /WSInternos/ProxyServices/PXSeguridadWS
	 * @return Mensaje firmado o un xml de error producido.
	 */
	public static XmlObject firmaMensajeAlgoritmoTimestamp (String mensaje, String aliasCertificado,String uriAlgoritmoFirma, String uriAlgoritmoDigest, String intervaloSegundosExpiracion, String endpoint)
	{
		XmlObject respuesta=null;
		//Datos del WSDL
		QName servicio = new QName("http://www.tributasenasturias.es/SeguridadWS/","Seguridad");
		QName port = new QName("http://www.tributasenasturias.es/SeguridadWS/","SeguridadSOAP");
		//Creamos el servicio y apuntamos al puerto
		Service service = Service.create(servicio);
		service.addPort(port, SOAPBinding.SOAP11HTTP_BINDING, endpoint);
		//Despachador de mensajes al servicio.
		Dispatch<SOAPMessage> dispatch = service.createDispatch(port, 
		SOAPMessage.class, Service.Mode.MESSAGE); //Enviamos el mensaje completo.
		
		try {
			MessageFactory mf = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
			//Creamos el mensaje de salida.
			SOAPMessage request = mf.createMessage();
			SOAPBody body = request.getSOAPBody();
			SOAPElement operacion = body.addChildElement(new QName("http://www.tributasenasturias.es/SeguridadWS/","firmarMensajeAlgoritmoConTimestamp"));
			SOAPElement nodoMensaje = operacion.addChildElement("mensaje");
			nodoMensaje.addTextNode(mensaje);
			nodoMensaje = operacion.addChildElement("aliasCertificado");
			nodoMensaje.addTextNode(aliasCertificado);
			nodoMensaje = operacion.addChildElement("uriAlgoritmoFirma");
			nodoMensaje.addTextNode(uriAlgoritmoFirma);
			nodoMensaje = operacion.addChildElement("uriAlgoritmoDigest");
			nodoMensaje.addTextNode(uriAlgoritmoDigest);
			nodoMensaje = operacion.addChildElement("intervaloSegundosExpiracion");
			nodoMensaje.addTextNode(intervaloSegundosExpiracion);
			request.saveChanges();//Aplicamos los cambios
			//Env�o para firmar.
			SOAPMessage response=dispatch.invoke(request);
			//Si ha llegado aqu�, no ha habido excepci�n, 
			//devolvemos el mensaje firmado.
			//Extraemos el nodo "mensajeFirmado"
			Node mensajeFirmado=selectSingleNode(response.getSOAPBody().getOwnerDocument(),"//*[local-name()='mensajeFirmado']/text()");
			respuesta=XmlObject.Factory.parse(mensajeFirmado.getNodeValue());
		} catch (SOAPException e) {
			try {
				respuesta = XmlObject.Factory.parse(setError(e.getMessage()));
				
			} catch (XmlException e1) {
				//Poco podemos hacer
				return null;
			}
		} catch (XmlException e) {
			try {
				respuesta = XmlObject.Factory.parse(setError(e.getMessage()));
				
			} catch (XmlException e1) {
				//Poco podemos hacer
				return null;
			}
		}
		return respuesta;
	}
	// FIN CRUBENCVS 47084 20/02/2023
}
