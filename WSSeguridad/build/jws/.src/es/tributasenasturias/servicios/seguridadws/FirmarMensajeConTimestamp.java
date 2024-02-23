
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
 *         &lt;element name="intervaloSegundosExpiracion" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "intervaloSegundosExpiracion"
})
@XmlRootElement(name = "firmarMensajeConTimestamp")
public class FirmarMensajeConTimestamp {

    @XmlElement(required = true)
    protected String mensaje;
    @XmlElement(required = true)
    protected String aliasCertificado;
    protected int intervaloSegundosExpiracion;

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
     * Gets the value of the intervaloSegundosExpiracion property.
     * 
     */
    public int getIntervaloSegundosExpiracion() {
        return intervaloSegundosExpiracion;
    }

    /**
     * Sets the value of the intervaloSegundosExpiracion property.
     * 
     */
    public void setIntervaloSegundosExpiracion(int value) {
        this.intervaloSegundosExpiracion = value;
    }

}
