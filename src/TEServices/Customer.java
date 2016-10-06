package TEServices;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Thanh
 */

@XmlRootElement

public class Customer {
	
	@XmlElement private int CustomerId;
	@XmlElement private String CustFirstName;
	@XmlElement private String CustLastName;
	@XmlElement  private String CustAddress;
	@XmlElement private String CustCity;
	@XmlElement private String CustProv;
	@XmlElement private String CustPostal;
	@XmlElement private String CustCountry;
	@XmlElement private String CustHomePhone;
	@XmlElement private String CustBusPhone;
	@XmlElement private String CustEmail;
	@XmlElement private int AgentId;
	@XmlElement private String CustUsername;
	@XmlElement private String CustPassword;
	
	
	public int getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}
	public String getCustFirstName() {
		return CustFirstName;
	}
	public void setCustFirstName(String custFirstName) {
		CustFirstName = custFirstName;
	}
	public String getCustLastName() {
		return CustLastName;
	}
	public void setCustLastName(String custLastName) {
		CustLastName = custLastName;
	}
	public String getCustAddress() {
		return CustAddress;
	}
	public void setCustAddress(String custAddress) {
		CustAddress = custAddress;
	}
	public String getCustCity() {
		return CustCity;
	}
	public void setCustCity(String custCity) {
		CustCity = custCity;
	}
	public String getCustProv() {
		return CustProv;
	}
	public void setCustProv(String custProv) {
		CustProv = custProv;
	}
	public String getCustPostal() {
		return CustPostal;
	}
	public void setCustPostal(String custPostal) {
		CustPostal = custPostal;
	}
	public String getCustCountry() {
		return CustCountry;
	}
	public void setCustCountry(String custCountry) {
		CustCountry = custCountry;
	}
	public String getCustHomePhone() {
		return CustHomePhone;
	}
	public void setCustHomePhone(String custHomePhone) {
		CustHomePhone = custHomePhone;
	}
	public String getCustBusPhone() {
		return CustBusPhone;
	}
	public void setCustBusPhone(String custBusPhone) {
		CustBusPhone = custBusPhone;
	}
	public String getCustEmail() {
		return CustEmail;
	}
	public void setCustEmail(String custEmail) {
		CustEmail = custEmail;
	}
	public int getAgentId() {
		return AgentId;
	}
	public void setAgentId(int agentId) {
		AgentId = agentId;
	}
	public String getCustUsername() {
		return CustUsername;
	}
	public void setCustUsername(String custUsername) {
		CustUsername = custUsername;
	}
	public String getCustPassword() {
		return CustPassword;
	}
	public void setCustPassword(String custPassword) {
		CustPassword = custPassword;
	}
	

}
