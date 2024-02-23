package es.tributasenasturias.servicios.seguridadws.implementacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


import org.apache.ws.security.WSSConfig;
import org.apache.ws.security.WSSecurityEngine;
import org.apache.ws.security.WSSecurityEngineResult;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.components.crypto.CryptoFactory;
import org.apache.ws.security.validate.Validator;
import org.w3c.dom.Document;


/**
 * Realiza la validación de la firma de WS Security, del tipo que se indica.
 * @author CarlosRuben
 *
 */
public class ValidadorFirma {
	private WSSecurityEngine secEngine = new WSSecurityEngine();
	private Crypto crypto = null;
	
	/**
	 * Constructor. Realiza la inicialización de la seguridad necesaria para las validaciones.
	 * @throws ValidadorException En caso de no poder construir el objeto.
	 */
	public ValidadorFirma() throws ValidadorException{
		WSSConfig.init();
		try {
			Properties prop = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("properties/crypto.properties");
			if (in!=null)
			{
				prop.load(in);
			}
			else
			{
				throw new ValidadorException ("No se pueden localizar las propiedades de 'Crypto'");
			}
			crypto = CryptoFactory.getInstance(prop);
		} catch (WSSecurityException e) {
			throw new ValidadorException ("Error al recuperar la instancia de 'Crypto':"+e.getMessage(),e);
		} catch (FileNotFoundException e) {
			throw new ValidadorException ("No existe el fichero de preferencias de Crypto:"+e.getMessage(),e);
		} catch (IOException e) {
			throw new ValidadorException ("Error de entrada/salida al recuperar el fichero de preferencias de Crypto:"+e.getLocalizedMessage(),e);
		}
	}
	/**
	 * Valida la firma WS Security del mensaje pasado por parámetro.
	 * @param soapMsg Mensaje SOAP en forma de cadena.
	 * @param tipoValidacion Tipo de validación a realizar. Ver {@link ValidacionConstants}.
	 * @return True si ha podido validar.
	 * @throws ValidadorException En caso de ocurrir un error de proceso que no sea debido a la validación.
	 * @throws WSSecurityException En caso de producirse un error de validación.
	 */
	public boolean validarFirma (String soapMsg, int tipoValidacion) throws ValidadorException, WSSecurityException
	{
		Validator validator = ValidacionMethodResolver.getMethod(tipoValidacion);
		WSSConfig config=new WSSConfig();
		//Si no ponemos esto, aquellos servicios que envíen su seguridad y no sigan a 
		//Basic Security Profile, fallarán en la validación. Los de Asturcon no lo hacen.
		config.setWsiBSPCompliant(false);
		config.setValidator(WSSecurityEngine.SIGNATURE, validator);
		secEngine.setWssConfig(config);
		Document doc=null;
		try
		{
			doc=Utils.string2Doc(soapMsg);
		}
		catch (Exception e)
		{
			throw new ValidadorException ("Error al convertir el mensaje a un documento XML:" + e.getMessage(),e);
		}
		List<WSSecurityEngineResult> result;
		result = secEngine.processSecurityHeader(doc, null, null, crypto);
		if (result==null)
		{
			throw new WSSecurityException("No se han encontrado cabeceras de seguridad, no se puede validar la seguridad del mensaje.");
		}
		return true;
	}
}
