package es.tributasenasturias.servicios.seguridadws.implementacion;


import org.apache.ws.security.validate.SignatureTrustValidator;
import org.apache.ws.security.validate.Validator;

/**
 * Permite conocer el método que implementará la validación de firma.
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
			throw new IllegalArgumentException ("Tipo de validación de firma no soportada.");
		}
		
		return v;
	}
}
