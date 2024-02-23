
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
 *         &lt;element name="soapMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "soapMsg"
})
@XmlRootElement(name = "validacionFirmaSinCertificado")
public class ValidacionFirmaSinCertificado {

    @XmlElement(required = true)
    protected String soapMsg;

    /**
     * Gets the value of the soapMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoapMsg() {
        return soapMsg;
    }

    /**
     * Sets the value of the soapMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoapMsg(String value) {
        this.soapMsg = value;
    }

}
