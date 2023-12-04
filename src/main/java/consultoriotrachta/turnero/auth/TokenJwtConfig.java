package consultoriotrachta.turnero.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;

public class TokenJwtConfig {

    //public final static String SECRET_KEY = "algun_tokem_con_alguna_frase_secreta";
    public final static SecretKey SECRET_KEY = Jwts.SIG.HS512.key().build();
    public final static String PREFIX_TOKEN = "Bearer ";
    public final static String HEADER_AUTHORIZATION = "authorization";


}
