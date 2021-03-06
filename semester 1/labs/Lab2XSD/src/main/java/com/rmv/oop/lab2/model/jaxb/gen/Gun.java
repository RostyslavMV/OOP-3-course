//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.04 at 01:31:27 PM EEST
//


package com.rmv.oop.lab2.model.jaxb.gen;

import lombok.ToString;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Gun complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Gun"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="isHandy" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="countryOfOrigin" type="{com.rmv/oop/lab2/model/jaxb/gen}Country"/&gt;
 *         &lt;element name="characteristics" type="{com.rmv/oop/lab2/model/jaxb/gen}GunCharacteristics"/&gt;
 *         &lt;element name="material" type="{com.rmv/oop/lab2/model/jaxb/gen}Material"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gun", propOrder = {
        "model",
        "isHandy",
        "countryOfOrigin",
        "characteristics",
        "material"
})
@ToString
public class Gun implements Comparable<Gun> {

    @XmlElement(required = true)
    protected String model;
    protected boolean isHandy;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Country countryOfOrigin;
    @XmlElement(required = true)
    protected GunCharacteristics characteristics;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Material material;
    @XmlAttribute(name = "id")
    protected BigInteger id;

    /**
     * Gets the value of the model property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the isHandy property.
     */
    public boolean isIsHandy() {
        return isHandy;
    }

    /**
     * Sets the value of the isHandy property.
     */
    public void setIsHandy(boolean value) {
        this.isHandy = value;
    }

    /**
     * Gets the value of the countryOfOrigin property.
     *
     * @return possible object is
     * {@link Country }
     */
    public Country getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * Sets the value of the countryOfOrigin property.
     *
     * @param value allowed object is
     *              {@link Country }
     */
    public void setCountryOfOrigin(Country value) {
        this.countryOfOrigin = value;
    }

    /**
     * Gets the value of the characteristics property.
     *
     * @return possible object is
     * {@link GunCharacteristics }
     */
    public GunCharacteristics getCharacteristics() {
        return characteristics;
    }

    /**
     * Sets the value of the characteristics property.
     *
     * @param value allowed object is
     *              {@link GunCharacteristics }
     */
    public void setCharacteristics(GunCharacteristics value) {
        this.characteristics = value;
    }

    /**
     * Gets the value of the material property.
     *
     * @return possible object is
     * {@link Material }
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the value of the material property.
     *
     * @param value allowed object is
     *              {@link Material }
     */
    public void setMaterial(Material value) {
        this.material = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    @Override
    public int compareTo(Gun o) {
        return (Integer.compare(this.characteristics.getSightingRange(), o.getCharacteristics().getSightingRange()));
    }
}
