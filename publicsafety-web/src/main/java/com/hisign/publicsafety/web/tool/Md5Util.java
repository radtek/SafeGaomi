package com.hisign.publicsafety.web.tool;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    private static final String TAG = Md5Util.class.getName();
    private static MessageDigest mMessageDigest;
    private static char mHexDigits[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    static {
        try {
            mMessageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            //Log.e("", TAG + " MessageDigest.getInstance() failed :" + e.toString());
        }
    }

    public static String getFileMD5String(String filePath) throws IOException {
        InputStream fis;
        fis = new FileInputStream(filePath);
        byte[] buf = new byte[1024];
        int numRead = 0;
        while ((numRead = fis.read(buf)) != -1) {
            mMessageDigest.update(buf, 0, numRead);
        }
        fis.close();
        return bufferToHex(mMessageDigest.digest());
    }
    
    public static String getFileMD5String(InputStream fis) throws IOException {
       
        byte[] buf = new byte[1024];
        int numRead = 0;
        while ((numRead = fis.read(buf)) != -1) {
            mMessageDigest.update(buf, 0, numRead);
        }
        fis.close();
        return bufferToHex(mMessageDigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringBuffer) {
        char c0 = mHexDigits[(bt & 0xf0) >> 4]; 
        char c1 = mHexDigits[bt & 0xf]; 
        stringBuffer.append(c0);
        stringBuffer.append(c1);
    }

}
