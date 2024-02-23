package es.tributasenasturias.servicios.seguridadws.implementacion;

import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.validate.Credential;
import org.apache.ws.security.validate.SignatureTrustValidator;
/**
 * Validador propio para la firma de WS Security. En este caso har� lo mismo 
 * que la clase ancestro (SignatureTrustValidator) pero al validar el certificado,
 * mientras exista, dir� que es v�lido, sin comprobar si se corresponde
 * a una emisora de confianza.
 * @author CarlosRuben
 *
 */
public class ValidadorFirmaSinCertificado extends SignatureTrustValidator {

/**
 * Sustituci�n de la validaci�n de certificado por una que s�lo comprueba que 
 * exista.
 */
@Override
public Credential validate(Credential credential, RequestData data)
		throws WSSecurityException {
	if (credential == null) {
        throw new WSSecurityException(WSSecurityException.FAILURE, "noCredential");
    }
	//Consideramos que el certificado siempre es v�lido, independientemente de que lo sea, se valida despu�s.
	return credential;
}
}
