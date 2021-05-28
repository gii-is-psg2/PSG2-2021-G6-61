package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.model.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class SupportController {

	@Value("${authUser}")
	private String authUser;
	@Value("${authPwd}")
	private String authPwd;
	@Value("${version}")
	private String version;
	@Value("${ItopURL}")
	private String apiURL;

	private final RestTemplate restTemplate;

	@Autowired
	public SupportController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping("/contact-us")
	public String supportPage(final Model model) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("auth_pwd", authPwd);
		map.add("auth_user", authUser);
		map.add("version", version);
		map.add("json_data", "{\"operation\":\"core/get\",\"class\":\"Person\","
				+ "\"key\":\"SELECT Person\"" 
				+ ",\"output_fields\":\"friendlyname, email, mobile_phone\"}");
		
		
		String jsonResponse = "";
		try {
			jsonResponse = this.restTemplate.postForObject(apiURL, map, String.class); 
			
		}catch (Exception e){
			model.addAttribute("message","ServiceUnavailable");
		}
		
		model.addAttribute("contacts", getContacts(jsonResponse));
		
		return "contact-us";
	}


	public List<Contact> getContacts(String jsonResponse){
		List<Contact> contacts = new ArrayList<Contact>();
		String[] jsonSplitted = jsonResponse.split("Person::");
		
		for(int i = 1; i < jsonSplitted.length; i++){
			String jsonString = String.valueOf(jsonSplitted[i]);
            String json = jsonString.replaceFirst("[0-9]+\":", "");
			JSONObject objects = new JSONObject(json);
			JSONObject fields = objects.getJSONObject("fields");
			Contact contact = new Contact(fields.getString("friendlyname"),fields.getString("email"),fields.getString("mobile_phone"));
			contacts.add(contact);
		}
		
		return contacts;
	}
}
