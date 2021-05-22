package com.url.controller;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.model.Encode;
import com.url.model.Url;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/url")
public class UrlController {

	@PostMapping("/encodeURL")
	public String urlEncode(@Valid @RequestBody Url url) {
		
		if (isValidURL(url.getUrl())) {			
			String encodedURL = Base64.getUrlEncoder().encodeToString(url.getUrl().getBytes());
			log.info("encodedURL: " + encodedURL);
			return encodedURL;
        }
        else{
        	return "Not A Valid URL";
        }

	}

	@PostMapping("/decodeURL")
	public String urlDecode(@Valid @RequestBody Encode encoded) {

		byte[] actualByte = Base64.getUrlDecoder().decode(encoded.getEncoded());
		String decodedURL = new String(actualByte);
		log.info("decodedURL: " + decodedURL);
		return decodedURL;

	}
	
	public boolean isValidURL(String url){
		 // Regex to check valid URL:
		//The URL must start with either http or https and
		//then followed by :// and
		//then it must contain www. and
		//then followed by subdomain of length (2, 256) and
		//last part contains top level domain like .com, .org etc.
        String regex = "((http|https)://)(www.)?"
              + "[a-zA-Z0-9@:%._\\+~#?&//=]"
              + "{2,256}\\.[a-z]"
              + "{2,6}\\b([-a-zA-Z0-9@:%"
              + "._\\+~#?&//=]*)";
 
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        if (url == null) {
            return false;
        }
        // Find match between given string
        // and regular expression
        // using Pattern.matcher()
        Matcher m = p.matcher(url);
 
        // Return if the string
        // matched the ReGex
        return m.matches();
	}
}
