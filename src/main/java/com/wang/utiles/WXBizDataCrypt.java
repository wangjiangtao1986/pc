package com.wang.utiles;


import java.io.UnsupportedEncodingException;  
import java.security.InvalidAlgorithmParameterException;  
import java.util.HashMap;  
import java.util.Map;  
  
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;  
import com.alibaba.fastjson.JSONObject;  
  
  
/** 
 * 对微信小程序用户加密数据的解密示例代码. 
 * @author Awoke 
 * @version 2018-1-24 
 * @see WXBizDataCrypt 
 * @since 
 */  
public class WXBizDataCrypt  
{  
  
    private String appid;  
  
    private String sessionKey;  
  
    public WXBizDataCrypt(String appid, String sessionKey)  
    {  
        this.appid = appid;  
        this.sessionKey = sessionKey;  
    }  
  
    /** 
     * 检验数据的真实性，并且获取解密后的明文. 
     * @param encryptedData string 加密的用户数据 
     * @param iv string 与用户数据一同返回的初始向量 
     * 
     * @return data string 解密后的原文 
     */  
    public String decryptData(String encryptedData, String iv)  
    {  
    	String userInfo = "";
        if (StringUtils.length(sessionKey) != 24)  
        {  
            return "ErrorCode::$IllegalAesKey;";  
        }  
        // 对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。  
        byte[] aesKey = Base64.decodeBase64(sessionKey);  
  
        if (StringUtils.length(iv) != 24)  
        {  
            return "ErrorCode::$IllegalIv;";  
        }  
        // 对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。  
        byte[] aesIV = Base64.decodeBase64(iv);  
  
        // 对称解密的目标密文为 Base64_Decode(encryptedData)  
        byte[] aesCipher = Base64.decodeBase64(encryptedData);  
  
        Map<String, String> map = new HashMap();  
  
        try  
        {  
            byte[] resultByte = AESUtil.decrypt(aesCipher, aesKey, aesIV);  
  
            if (null != resultByte && resultByte.length > 0)  
            {  
                userInfo = new String(resultByte, "UTF-8");  
                map.put("code", "0000");  
                map.put("msg", "succeed");  
                map.put("userInfo", userInfo);  
                  
                // watermark参数说明：  
                // 参数  类型  说明  
                // watermark   OBJECT  数据水印  
                // appid   String  敏感数据归属appid，开发者可校验此参数与自身appid是否一致  
                // timestamp   DateInt 敏感数据获取的时间戳, 开发者可以用于数据时效性校验'  
                  
                // 根据微信建议：敏感数据归属appid，开发者可校验此参数与自身appid是否一致  
                // if decrypted['watermark']['appid'] != self.appId:  
                JSONObject jsons = JSON.parseObject(userInfo);  
                String id = jsons.getJSONObject("watermark").getString("appid");  
                if(!StringUtils.equals(id, appid))  
                {  
                    return "ErrorCode::$IllegalBuffer;";  
                }  
            }  
            else  
            {  
                map.put("status", "1000");  
                map.put("msg", "false");  
            }  
        }  
        catch (InvalidAlgorithmParameterException e)  
        {  
            e.printStackTrace();  
        }  
        catch (UnsupportedEncodingException e)  
        {  
            e.printStackTrace();  
        }  
  
        return userInfo;  
    }  
  
    /** 
     *   
     * @param args  
     * @see  
     */  
    public static void main(String[] args)  
    {  
        String appId = "wxa4a76bcefc655c8f";  
        String sessionKey = "d5da2b0cba4777b55f5aa973f09cb00b==";  
        String encryptedData = "KkOg4dBFazNAh36eEKJupq6j0F9MccLSsY2NmfQKShWDox83WDG+gwhLwBL1mhmfcnq8QJ1VWckEasADDsyyHNOSdHbRS6a8LK9D7ZcBNFFRHkSQYkX3vDVh1fHIe9JAOt83fbK7xlCUHHaxWl4c0XEjEgaqFMtPYWgp2FMJ5l9OOa6Uq1evnLP5sPql05S42/ceV2w8doGfDxPsHO2Xc1CpAJQFNjm2DnBJoyhiTq4BJP3S8+HD/mgKd4M5gI5BMGLd/5flzu2zDkewIsd3S8SDyGC1DVZ+BYffiakmid129UbOpcis9t+FTSMJdJhgPHgx1Z4A6rW5z+ptNASenL9q45jy4iq8XM0HcSb42K6ztyFj5mWwRA0iwHiWFYcuTLj7Npj0UW8xOEURLAn9aRUyAPd5ox6QxbCfB6prRqZRfz/AZALd+GkyQ/8nVXL3dFgZUURaNUwORWPH3LYTGNx5atxIDv4GOxhRqwlZZnw=";  
        String iv = "vnhLhvRikDffxA6GMkUFzg==";  
  
        WXBizDataCrypt biz = new WXBizDataCrypt(appId, sessionKey);  
          
        System.out.println(biz.decryptData(encryptedData, iv));  
  
    }  
}  