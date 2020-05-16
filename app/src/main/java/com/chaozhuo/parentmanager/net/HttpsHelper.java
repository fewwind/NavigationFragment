package com.chaozhuo.parentmanager.net;


import android.os.Build;
import android.text.TextUtils;

import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;


public class HttpsHelper {

    private static String PUBLIC_MD5 = "f0bde905bec317726d7ad4d4506126e5";

    public static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return ssfFactory;
    }

    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            checkServerWrapper(chain, authType);
        }

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            if (isNeedCheck()) getSystemDefault().checkClientTrusted(x509Certificates, s);
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return getAcceptWraper();
        }
    }

    private static void checkServerWrapper(X509Certificate[] chain, String authType) {
        if (isNeedCheck()) {
            try {
                getSystemDefault().checkServerTrusted(chain, authType);
            } catch (CertificateException e) {
                e.printStackTrace();
            }
        } else {
            if (chain == null || chain.length <= 0)
                throw new IllegalArgumentException("check server x509 is null");
            for (X509Certificate cer : chain) {
                Principal subjectDN = cer.getSubjectDN();
                if (subjectDN != null) {
                    String name = subjectDN.getName();
                    if (!TextUtils.isEmpty(name) && name.contains("360.cn")) {
                        byte[] encoded = cer.getPublicKey().getEncoded();
                        if (!md5(encoded).equals(PUBLIC_MD5))
                            throw new IllegalArgumentException(md5(encoded) + "-->check server publicKey is illegal");
                    }
                }

            }
        }
    }

    private static X509Certificate[] getAcceptWraper() {
        if (isNeedCheck()) {
            return getSystemDefault().getAcceptedIssuers();
        } else return new X509Certificate[0];
    }

    private static boolean isNeedCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (getSystemDefault() != null)
                return false;
        }
        return false;
    }

    private static X509TrustManager getSystemDefault() {
        try {
            TrustManagerFactory managerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            managerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = managerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("unexpected default trust manager");
            }
            return (X509TrustManager) trustManagers[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //信任所有的服务器,返回true
    public static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static String md5(byte[] string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string);
            StringBuilder result = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xff) < 0x10) result.append("0");
                result.append(Integer.toHexString(b & 0xff));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}