package es.tributasenasturias.servicios.seguridadws.implementacion;

import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.validate.Credential;
import org.apache.ws.security.validate.SignatureTrustValidator;
/**
 * Validador propio para la firma de WS Security. En este caso hará lo mismo 
 * que la clase ancestro (SignatureTrustValidator) pero al validar el certificado,
 * mientras exista, dirá que es válido, sin comprobar si se corresponde
 * a una emisora de confianza.
 * @author CarlosRuben
 *
 */
public class ValidadorFirmaSinCertificado extends SignatureTrustValidator {

/**
 * Sustitución de la validación de certificado por una que sólo comprueba que 
 * exista.
 */
@Override
public Credential validate(Credential credential, RequestData data)
		throws WSSecurityException {
	if (credential == null) {
        throw new WSSecurityException(WSSecurityException.FAILURE, "noCredential");
    }
	//Consideramos que el certificado siempre es válido, independientemente de que lo sea, se valida después.
	return credential;
}
}
