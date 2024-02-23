
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
 *         &lt;element name="mensajeFirmado" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "mensajeFirmado"
})
@XmlRootElement(name = "firmarMensajeAlgoritmoConTimestampResponse")
public class FirmarMensajeAlgoritmoConTimestampResponse {

    @XmlElement(required = true)
    protected String mensajeFirmado;

    /**
     * Gets the value of the mensajeFirmado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeFirmado() {
        return mensajeFirmado;
    }

    /**
     * Sets the value of the mensajeFirmado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeFirmado(String value) {
        this.mensajeFirmado = value;
    }

}
