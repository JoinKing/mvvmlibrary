package com.hwq.mvvmlibrary.ssm;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SM4Utils {

    public String secretKey = "";
    public String iv = "";
    private boolean hexString = false;

    public SM4Utils()
    {
    }

    public String encryptData_ECB(String plainText)
    {
        try
        {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            keyBytes = secretKey.getBytes();
            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes("UTF-8"));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0)
            {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptData_ECB(String cipherText)
    {
        try
        {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            keyBytes = secretKey.getBytes();
            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] bytes = new BASE64Decoder().decodeBuffer(cipherText);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx,bytes);
            return new String(decrypted, "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String encryptData_CBC(String plainText){
        try{
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            byte[] ivBytes;

            keyBytes = secretKey.getBytes();
            ivBytes = iv.getBytes();

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes("UTF-8"));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0)
            {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptData_CBC(String cipherText)
    {
        try
        {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString)
            {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(iv);
            }
            else
            {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] bytes = new BASE64Decoder().decodeBuffer(cipherText);
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, bytes);
            return new String(decrypted, "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static String getOrderIdByUUId() {
        String hashCodeV = UUID.randomUUID().toString().replaceAll("-","").trim();
        String uuid = "";
        if(hashCodeV.length() < 16){
            String str = "";
            for(int i = 16-hashCodeV.length();i>0;i--){
                str+="0";
            }
            uuid = str +hashCodeV;
        }else{
            uuid = hashCodeV.substring(0,16);
        }

        return uuid;
    }

}
