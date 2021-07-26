package com.tchuhu.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tchuhu.exception.CheckedException;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


/**
 * Miscellaneous utilities for web applications.
 *
 * @author mark
 */
@Slf4j
@UtilityClass
public class WebUtils extends org.springframework.web.util.WebUtils {
	private final String BASIC_ = "Basic ";
	private final String UNKNOWN = "unknown";
	public final String HEADER_SECRET_KEY = "ShiYouSecretKey";
	public static final String SHI_YOU_BODY_PARAM = "SHI_YOU_BODY_PARAM";
	private static final String KEY_ALGORITHM = "AES";
	private static final String SHI_YOU_SECRET_PARAM = "shiYouSecretParam";
	/**
		* 用于前端敏感信息加密 秘密
	 */
	public static final String RSA_PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMO9hl2xO0XD3IU6EoFNIMLlXTVE26/Gh2QDQs94NElGQC6T3u98vFoi0B6pGjZiaJ6lBweomSvtqdfcn9MBR/oJEWp7Ay+KXwbsg8LMFm8uqwSvDEv2XE0Zf0qCyTdyw4rxdZ91t/6rEKp3F313b1X84ytbj3YMlscXrEo9X3rwIDAQAB";

	/**
	 * 后端敏感信息解密
	 */
	public static final String RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIw72GXbE7RcPchToSgU0gwuVdNUTbr8aHZANCz3g0SUZALpPe73y8WiLQHqkaNmJonqUHB6iZK+2p19yf0wFH+gkRansDL4pfBuyDwswWby6rBK8MS/ZcTRl/SoLJN3LDivF1n3W3/qsQqncXfXdvVfzjK1uPdgyWxxesSj1fevAgMBAAECgYADoHydTcg5rvUOHFObtMYl+IDRwWltP5/4BvsSeqhTer8HcD7g0lnb17DwTr/LiRXQFVZVQmYSfIss7ZbuzHKLPIk+5NeyEAhh5aHWvLidhgKzk0KXW5RdVa86pOHUN4/vAD4lA5k2D/WgI4yaP3jqY8hOMM2ef96LTyt7Ap1wKQJBAOHCBRcBybT7x/5OF4bOTjG9060dMA5pssGS7Wkn8qOOUoWNGT2BEulHzdDD06iSBL4+kGm7yb+i+3rP/9NiBfsCQQCfBOwYhX7nKmUm9tcVgmO5IvTJmcsJiBcwtWJz9YKPoSMAb+81D8qertlIJIjVwq0qSp2/3A5xatT9fsqKAQrdAkEAtMQiRzXLSPuCPAJTnEF9Ix8Yazk/zYqc85quNtEh3AyNzxoX58N+XQR9rHfg9s1UtvBpuf//n07lhoUeFygaGQJAM1GFWAyM/dFCv6Zdl0VzJWZQlVcanULcKQ4AbefHcM7R2uxEyF0GSNuop42S01l2RPW7nxVHW/bcMcrfVlYryQJACZA/ZOLcjQnj0QHY3oEK+fdGVZjrbLGPJkHNdjHQRAGZ5oTOzCmbB9PxpDRGuZzaQCBCbXKHop7eV3xSYuDtMw==";

	/**
	 * rsa私钥解密
	 * @param data 密文
	 * @return
	 */
	public static String decryptRSA(String data) {
		RSA rsa = new RSA(RSA_PRIVATE_KEY, RSA_PUB_KEY);
		return rsa.decryptStr(data, KeyType.PrivateKey);
	}

	/**
	 * aes解密
	 * @param data
	 * @param secretKey
	 * @return
	 */
	public static String decryptAES(String data, String secretKey) {
		AES aes = new AES(Mode.CBC, Padding.NoPadding,
			new SecretKeySpec(secretKey.getBytes(), KEY_ALGORITHM),
			new IvParameterSpec(secretKey.getBytes()));
		byte[] result = aes.decrypt(Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
		return new String(result, StandardCharsets.UTF_8);
	}

	/**
	 * 判断是否ajax请求
	 * spring ajax 返回含有 ResponseBody 或者 RestController注解
	 *
	 * @param handlerMethod HandlerMethod
	 * @return 是否ajax请求
	 */
	public boolean isBody(HandlerMethod handlerMethod) {
		ResponseBody responseBody = ClassUtils.getAnnotation(handlerMethod, ResponseBody.class);
		return responseBody != null;
	}

	public void saveBodyParam(String body){
		getRequest().setAttribute(SHI_YOU_BODY_PARAM, body);
	}

	public String getBodyParam(){
		return StrUtil.toString(getRequest().getAttribute(SHI_YOU_BODY_PARAM));
	}

	/**
	 * 读取cookie
	 *
	 * @param name cookie name
	 * @return cookie value
	 */
	public String getCookieVal(String name) {
		HttpServletRequest request = WebUtils.getRequest();
		Assert.notNull(request, "request from RequestContextHolder is null");
		return getCookieVal(request, name);
	}

	/**
	 * 读取cookie
	 *
	 * @param request HttpServletRequest
	 * @param name    cookie name
	 * @return cookie value
	 */
	public String getCookieVal(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		return cookie != null ? cookie.getValue() : null;
	}

	/**
	 * 清除 某个指定的cookie
	 *
	 * @param response HttpServletResponse
	 * @param key      cookie key
	 */
	public void removeCookie(HttpServletResponse response, String key) {
		setCookie(response, key, null, 0);
	}

	/**
	 * 设置cookie
	 *
	 * @param response        HttpServletResponse
	 * @param name            cookie name
	 * @param value           cookie value
	 * @param maxAgeInSeconds maxage
	 */
	public void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAgeInSeconds);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}

	/**
	 * 获取 HttpServletRequest
	 *
	 * @return {HttpServletRequest}
	 */
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 获取 HttpServletResponse
	 *
	 * @return {HttpServletResponse}
	 */
	public HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	/**
	 * 返回json
	 *
	 * @param response HttpServletResponse
	 * @param result   结果对象
	 */
	public void renderJson(HttpServletResponse response, Object result) {
		renderJson(response, result, MediaType.APPLICATION_JSON_UTF8_VALUE);
	}

	/**
	 * 返回json
	 *
	 * @param response    HttpServletResponse
	 * @param result      结果对象
	 * @param contentType contentType
	 */
	public void renderJson(HttpServletResponse response, Object result, String contentType) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType(contentType);
		try (PrintWriter out = response.getWriter()) {
			out.append(JSONUtil.toJsonStr(result));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取ip
	 *
	 * @return {String}
	 */
	public String getIP() {
		return getIP(WebUtils.getRequest());
	}

	/**
	 * 获取ip
	 *
	 * @param request HttpServletRequest
	 * @return {String}
	 */
	public String getIP(HttpServletRequest request) {
		Assert.notNull(request, "HttpServletRequest is null");
		String ip = request.getHeader("X-Requested-For");
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return StringUtils.isBlank(ip) ? null : ip.split(",")[0];
	}


	public String getRequestBody(HttpServletRequest request) {
		int len = request.getContentLength();
		byte[] buff = new byte[len];
		try {
			request.getInputStream().read(buff, 0, len);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(buff);
	}

	/**
	 * 获取加密秘钥
	 * @return
	 */
	public String getSecretKey() {
		return getRequest().getHeader(HEADER_SECRET_KEY);
	}
	/**
	 * 从request 获取CLIENT_ID
	 *
	 * @return
	 */
	@SneakyThrows
	public String[] getClientId(ServerHttpRequest request) {
		String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

		if (header == null || !header.startsWith(BASIC_)) {
			throw new CheckedException("请求头中client信息为空");
		}
		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		} catch (IllegalArgumentException e) {
			throw new CheckedException(
				"Failed to decode basic authentication token");
		}

		String token = new String(decoded, StandardCharsets.UTF_8);
		return token.split(":");
	}

	/**
	 * body参数解密 AES + RSA
	 * @param body
	 * @return
	 */
	public static String decryptParam(String body) {
		try {
			JSONObject bodyJSON = JSON.parseObject(body);
			String secretBody = bodyJSON.getString(SHI_YOU_SECRET_PARAM);
			// 获取加密秘钥
			String secretKey = getSecretKey();
			if (StrUtil.isNotBlank(secretBody)) {
				body = decryptString(secretBody, secretKey);
			}
			return body;
		} catch (Exception e) {
			log.error("参数解密失败", e);
			return body;
		}
	}

	/**
	 * 参数解密 AES + RSA
	 * @param secretStr 加密字符串
	 * @return
	 */
	public static String decryptString(String secretStr, String secretKey) {
		try {
			// 获取AES秘钥
			log.debug("解密参数: {}", secretStr);
			if (StrUtil.isNotBlank(secretKey)) {
				// 解密秘钥
				secretKey = decryptRSA(secretKey);
				// 用秘钥解密参数
				secretStr = WebUtils.decryptAES(secretStr, secretKey);
			}
			return secretStr;
		} catch (Exception e) {
			log.error("参数解密失败", e);
			return secretStr;
		}
	}
}

