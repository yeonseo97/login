package com.demo.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderUtil {
	
    public static String hashPassword(String password, String salt) {
        try {
            // 비밀번호와 솔트를 결합
            String passwordWithSalt = password + salt;
            
        	// MessageDigest 객체 생성 : SHA-256 해시함수를 사용(256비트(32바이트) 해시를 생성)
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 매개변수로 전달된 비밀번호를 바이트 배열로 변환
            // digest를 사용하여 비밀번호(문자열)의 해시값을 계산하여 바이트 배열로 반환
            byte[] encodedHash = digest.digest(passwordWithSalt.getBytes(StandardCharsets.UTF_8));

            // bytes를 16진수 문자열로 변환
            // 새로운 문자열을 생성하기 위한 StringBuilder 객체 생성
            // 문자열을 동적으로 빠르게 생성하는 데 사용
            StringBuilder hexString = new StringBuilder();
            // 해시된 비밀번호
            for (byte hashByte : encodedHash) {
            	// toString은 toHexString의 상위 함수(16진수 문자열로 변환)
            	// 0xff & hashByte : 바이트를 부호 없는 8비트 정수로 변환하는 부분
            	// 바이트 값이 음수일 경우 부호 비트가 추가되기 때문에 부호 없는 정수로 변환하여 비트 연산이 제대로 동작하게 함
            	// 바이트를 부호 있는 정수로 변환하면 음수 값이 나올 수 있으며, 이로 인해 예상치 못한 동작이 발생할 수 있기 때문
                String hex = Integer.toHexString(0xff & hashByte);
                // 각 16진수 문자열은 1자리 또는 2자리이다. 만약 16진수 문자열이 1자리이면, 
                // 예를 들어 "a" 대신 "0a"와 같이 2자리로 표현되어야 한다. 
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                // 현재의 16진수 문자열 hex를 hexString에 추가. 
                // 각 바이트의 16진수 문자열이 계속해서 hexString에 추가되며, 
                // 최종적으로 해시된 비밀번호의 16진수 문자열 표현이 생성
                hexString.append(hex);
            }

            return hexString.toString();
            // 알고리즘 이름이 잘못된 경우  
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String generateRandomSalt() {
        // 16 바이트 길이의 솔트(배열) 생성
        byte[] saltBytes = new byte[16];

        // SecureRandom을 사용하여 무작위 바이트로 채움
        SecureRandom random = new SecureRandom();
        random.nextBytes(saltBytes);

        // 생성된 바이트 배열을 Base64 인코딩하여 문자열로 반환
        return Base64.getEncoder().encodeToString(saltBytes);
    }

}
