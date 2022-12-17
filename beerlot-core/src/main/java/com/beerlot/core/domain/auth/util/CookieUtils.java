package com.beerlot.core.domain.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class CookieUtils {

    public static final String COOKIE_NAME_REDIRECT_URL = "beerlot-oauth-redirect-url";
    public static final String COOKIE_NAME_AUTH_REQUEST = "beerlot-oauth-auth-request";

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

    public static void addCookie(HttpServletResponse response,
                                 String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request,
                                    HttpServletResponse response,
                                    String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static String serialize(Object object) {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> clazz) {
        return clazz.cast(SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.getValue())));
    }
}
