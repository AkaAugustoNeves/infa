package br.com.pilares.infagateway.util;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.pilares.infagateway.model.dto.SubjectDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
    private String secret;
	
	@PostConstruct
    public void init(){
		
    }
	
	 public Claims getAllClaimsFromToken(String token) {
	    return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token.substring(7, token.length())).getBody();
	 }
	 
	public SubjectDTO getSubject(Claims claims) {
		System.out.println("7777");
		System.out.println(claims);
		//Claims jsonSub = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token.substring(7, token.length())).getBody();
		//System.out.println(jsonSub.getSubject());
		Gson g = new Gson();
		return g.fromJson(claims.getSubject(), SubjectDTO.class);
	}

    public boolean isTokenExpired(String token) {
  
    	if(!(Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token.substring(7, token.length())).getBody().getExpiration().getTime() > new Date().getTime()) == false) {
    		return false;
    	}
    	return true;
    }

    public boolean isInvalid(String token) {
    	return this.isTokenExpired(token);
    }
    
    public boolean isTokenValid(String token) {
    	return !(isTokenExpired(token));
		/*try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}catch (Exception e) {
			System.out.println("veio pro catch");
			return false;
		}*/
	}	

}
