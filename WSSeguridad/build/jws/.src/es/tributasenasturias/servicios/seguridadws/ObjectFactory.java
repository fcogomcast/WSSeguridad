
package es.tributasenasturias.servicios.seguridadws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.tributasenasturias.servicios.seguridadws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ValidacionFirmaCompletaResponse_QNAME = new QName("http://www.tributasenasturias.es/SeguridadWS/", "validacionFirmaCompletaResponse");
    private final static QName _ValidacionFirmaSinCertificadoResponse_QNAME = new QName("http://www.tributasenasturias.es/SeguridadWS/", "validacionFirmaSinCertificadoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.tributasenasturias.servicios.seguridadws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FirmarMensajeConTimestampResponse }
     * 
     */
    public FirmarMensajeConTimestampResponse createFirmarMensajeConTimestampResponse() {
        return new FirmarMensajeConTimestampResponse();
    }

    /**
     * Create an instance of {@link FirmarMensajeAlgoritmoResponse }
     * 
     */
    public FirmarMensajeAlgoritmoResponse createFirmarMensajeAlgoritmoResponse() {
        return new FirmarMensajeAlgoritmoResponse();
    }

    /**
     * Create an instance of {@link ValidacionResponse }
     * 
     */
    public ValidacionResponse createValidacionResponse() {
        return new ValidacionResponse();
    }

    /**
     * Create an instance of {@link ValidacionFirmaCompleta }
     * 
     */
    public ValidacionFirmaCompleta createValidacionFirmaCompleta() {
        return new ValidacionFirmaCompleta();
    }

    /**
     * Create an instance of {@link FirmarMensajeAlgoritmo }
     * 
     */
    public FirmarMensajeAlgoritmo createFirmarMensajeAlgoritmo() {
        return new FirmarMensajeAlgoritmo();
    }

    /**
     * Create an instance of {@link FirmarMensajeConTimestamp }
     * 
     */
    public FirmarMensajeConTimestamp createFirmarMensajeConTimestamp() {
        return new FirmarMensajeConTimestamp();
    }

    /**
     * Create an instance of {@link FirmarMensajeAlgoritmoConTimestampResponse }
     * 
     */
    public FirmarMensajeAlgoritmoConTimestampResponse createFirmarMensajeAlgoritmoConTimestampResponse() {
        return new FirmarMensajeAlgoritmoConTimestampResponse();
    }

    /**
     * Create an instance of {@link FirmarMensajeAlgoritmoConTimestamp }
     * 
     */
    public FirmarMensajeAlgoritmoConTimestamp createFirmarMensajeAlgoritmoConTimestamp() {
        return new FirmarMensajeAlgoritmoConTimestamp();
    }

    /**
     * Create an instance of {@link FirmarMensaje }
     * 
     */
    public FirmarMensaje createFirmarMensaje() {
        return new FirmarMensaje();
    }

    /**
     * Create an instance of {@link ValidacionFirmaSinCertificado }
     * 
     */
    public ValidacionFirmaSinCertificado createValidacionFirmaSinCertificado() {
        return new ValidacionFirmaSinCertificado();
    }

    /**
     * Create an instance of {@link FirmarMensajeResponse }
     * 
     */
    public FirmarMensajeResponse createFirmarMensajeResponse() {
        return new FirmarMensajeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidacionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tributasenasturias.es/SeguridadWS/", name = "validacionFirmaCompletaResponse")
    public JAXBElement<ValidacionResponse> createValidacionFirmaCompletaResponse(ValidacionResponse value) {
        return new JAXBElement<ValidacionResponse>(_ValidacionFirmaCompletaResponse_QNAME, ValidacionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidacionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tributasenasturias.es/SeguridadWS/", name = "validacionFirmaSinCertificadoResponse")
    public JAXBElement<ValidacionResponse> createValidacionFirmaSinCertificadoResponse(ValidacionResponse value) {
        return new JAXBElement<ValidacionResponse>(_ValidacionFirmaSinCertificadoResponse_QNAME, ValidacionResponse.class, null, value);
    }

}
