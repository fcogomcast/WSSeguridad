package es.tributasenasturias.servicios.seguridadws.implementacion;


import org.apache.ws.security.validate.SignatureTrustValidator;
import org.apache.ws.security.validate.Validator;

/**
 * Permite conocer el m�todo que implementar� la validaci�n de firma.
 * @author CarlosRuben
 *
 */
public class ValidacionMethodResolver {
	public static Validator getMethod (int tipoValidacion)
	{
		Validator v=null;
		switch (tipoValidacion)
		{
		case ValidacionConstants.VALIDACION_COMPLETA:
			v=new SignatureTrustValidator();
			break;
		case ValidacionConstants.VALIDACION_SIN_CERTIFICADO:
			v=new ValidadorFirmaSinCertificado();
			break;
		default:
			throw new IllegalArgumentException ("Tipo de validaci�n de firma no soportada.");
		}
		
		return v;
	}
}
