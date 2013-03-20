/**
 * 
 */
package com.nervytech.mailer24x7.integrations.crm.clients;

import java.util.List;

import com.nervytech.mailer24x7.integrations.crm.highrise.Highrise;
import com.nervytech.mailer24x7.integrations.crm.highrise.PeopleManager;
import com.nervytech.mailer24x7.integrations.crm.highrise.Person;
import com.temboo.core.TembooException;

/**
 * @author bsikkaya
 *
 */
public class HighRiseClient {

	/**
	 * 
	 */
	public HighRiseClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<Person> getAllContacts(String accountName, String token){
		Highrise highRise = new Highrise("nervytech2", "4aeef317c3ec20a1e4edb1618c378096");
		PeopleManager peopleManager = highRise.getPeopleManager();
		return peopleManager.getAll(100l);
		
	}

	/**
	 * @param args
	 * @throws TembooException 
	 */
	public static void main(String[] args) {
		
		List<Person> allContacts = getAllContacts("nervytech2", "4aeef317c3ec20a1e4edb1618c378096");
		
		System.out.println("Peopleeeeeeeeeeeeeeeee "+allContacts);
		for(Person person: allContacts) {
			System.out.println("EMMMMMMMM "+person.getFirstName()+" Email Id "+person.getContactData().getEmailAddresses().get(0).getAddress());
		}
		
        
	}

}
