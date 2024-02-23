
package es.tributasenasturias.servicios.seguridadws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="aliasCertificado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uriAlgoritmoFirma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uriAlgoritmoDigest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mensaje",
    "aliasCertificado",
    "uriAlgoritmoFirma",
    "uriAlgoritmoDigest"
})
@XmlRootElement(name = "firmarMensajeAlgoritmo")
public class FirmarMensajeAlgoritmo {

    @XmlElement(required = true)
    protected String mensaje;
    @XmlElement(required = true)
    protected String aliasCertificado;
    @XmlElement(required = true)
    protected String uriAlgoritmoFirma;
    @XmlElement(required = true)
    protected String uriAlgoritmoDigest;

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Gets the value of the aliasCertificado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliasCertificado() {
        return aliasCertificado;
    }

    /**
     * Sets the value of the aliasCertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliasCertificado(String value) {
        this.aliasCertificado = value;
    }

    /**
     * Gets the value of the uriAlgoritmoFirma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriAlgoritmoFirma() {
        return uriAlgoritmoFirma;
    }

    /**
     * Sets the value of the uriAlgoritmoFirma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriAlgoritmoFirma(String value) {
        this.uriAlgoritmoFirma = value;
    }

    /**
     * Gets the value of the uriAlgoritmoDigest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriAlgoritmoDigest() {
        return uriAlgoritmoDigest;
    }

    /**
     * Sets the value of the uriAlgoritmoDigest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriAlgoritmoDigest(String value) {
        this.uriAlgoritmoDigest = value;
    }

}
