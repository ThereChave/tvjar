package com.github.catvod.spider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;

import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.utils.Misc;
import com.github.catvod.utils.okhttp.OkHttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Bulei extends Spider {
    private static final String siteUrl = "https://www.bulei.top";

    private HashMap<String, String> getHeaders(String url, String data) {
        HashMap<String, String> headers = new HashMap<>();
        if (data != null) {
            String token = "";
            try {
                token = Base64.encodeToString(b(fakeDevice.getBytes("UTF-8"), tokenKey == null ? "XPINGGUO" : tokenKey), 2);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            int currentTimeMillis = (int) (System.currentTimeMillis() / ((long) 1000));
            String hash = md5(fakeDevice + data + currentTimeMillis).substring(8, 12);
            headers.put("token", token);
            headers.put("hash", hash);
            headers.put("timestamp", currentTimeMillis + "");
            if (tokenKey == null) {
                headers.put("version", "ANDROID cn.grelighting.xpg1.1.5");
            }
        }
        headers.put("User-Agent", "okhttp/4.9.1");
        return headers;
    }

    private String fakeDevice = null;
    private String tokenKey = null;

    @Override
    public void init(Context context, String extend) {
        super.init(context, extend);
        SharedPreferences sharedPreferences = context.getSharedPreferences("spider_LiteApple", Context.MODE_PRIVATE);
        try {
            fakeDevice = sharedPreferences.getString("didd", null);
        } catch (Throwable th) {
        } finally {
            if (fakeDevice == null) {
                fakeDevice = fakeDid();
                sharedPreferences.edit().putString("didd", fakeDevice).commit();
            }
        }
    }
    @Override
    public String homeContent(boolean filter) {
        try {
            String url = siteUrl + "/api.php/provide/vod/";
            String content = OkHttpUtil.string(url, getHeaders(url, ""));
            JSONObject dataObject = new JSONObject(decryptResponse(content));
            JSONArray jsonArray = dataObject.getJSONArray("list");
            JSONArray videos = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject vObj = jsonArray.getJSONObject(i);
                String title = vObj.getString("vod_name");
                JSONObject v = new JSONObject();
                v.put("vod_id", vObj.getString("vod_id"));
                v.put("vod_name", title);
                v.put("vod_pic", vObj.getString("vod_pic"));
                v.put("vod_remarks", vObj.getString("vod_remarks"));
                videos.put(v);
            }
            JSONObject result = new JSONObject();
            result.put("list", videos);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    @Override
    public String homeVideoContent() {
        try {
            String url = siteUrl + "/api.php/provide/vod/";
            String content = OkHttpUtil.string(url, getHeaders(url, ""));
            JSONObject dataObject = new JSONObject(decryptResponse(content));
            JSONArray jsonArray = dataObject.getJSONArray("list");
            JSONArray videos = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject vObj = jsonArray.getJSONObject(i);
                String title = vObj.getString("vod_name");
                JSONObject v = new JSONObject();
                v.put("vod_id", vObj.getString("vod_id"));
                v.put("vod_name", title);
                v.put("vod_pic", vObj.getString("vod_pic"));
                v.put("vod_remarks", vObj.getString("vod_remarks"));
                videos.put(v);
            }
            JSONObject result = new JSONObject();
            result.put("list", videos);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    @Override
    public String categoryContent(String tid, String pg, boolean filter, HashMap<String, String> extend) {
        try {
            String url = siteUrl + "/api.php/provide/vod/&t=&pg=" + pg + "&h=&ids=&wd=";
            String content = OkHttpUtil.string(url, getHeaders(url, ""));
            JSONObject dataObject = new JSONObject(decryptResponse(content));
            JSONArray jsonArray = dataObject.getJSONArray("list");
            JSONArray videos = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject vObj = jsonArray.getJSONObject(i);
                String title = vObj.getString("vod_name");
                JSONObject v = new JSONObject();
                v.put("vod_id", vObj.getString("vod_id"));
                v.put("vod_name", title);
                v.put("vod_pic", vObj.getString("vod_pic"));
                v.put("vod_remarks", vObj.getString("vod_remarks"));
                videos.put(v);
            }
            JSONObject result = new JSONObject();
            int limit = 20;
            int page = Integer.parseInt(pg);
            result.put("page", page);
            int pageCount = videos.length() == limit ? page + 1 : page;
            result.put("pagecount", pageCount);
            result.put("limit", limit);
            result.put("total", Integer.MAX_VALUE);
            result.put("list", videos);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    @Override
    public String detailContent(List<String> ids) {
        try {
            String url = siteUrl + "/api.php/provide/vod/?ac=videolist&ids=" + ids.get(0);
            String content = OkHttpUtil.string(url, getHeaders(url, ids.get(0)));
            JSONObject dataObject = new JSONObject(decryptResponse(content));
            JSONArray jsonArray = dataObject.getJSONArray("list");
            JSONObject vObj = jsonArray.getJSONObject(0);
            JSONObject result = new JSONObject();
            JSONArray list = new JSONArray();
            JSONObject vodAtom = new JSONObject();
            vodAtom.put("vod_id", vObj.getString("vod_id"));
            vodAtom.put("vod_name", vObj.getString("vod_name"));
            vodAtom.put("vod_pic", vObj.getString("vod_pic"));
            vodAtom.put("type_name", vObj.getString("vod_class"));
            vodAtom.put("vod_year", vObj.getString("vod_year"));
            vodAtom.put("vod_area", vObj.getString("vod_area"));
            vodAtom.put("vod_remarks", vObj.getString("vod_pubdate"));
            vodAtom.put("vod_actor", vObj.getString("vod_actor"));
            vodAtom.put("vod_content", vObj.getString("vod_content").trim());

            String play_url = vObj.getString("vod_play_url");
            String play_from = vObj.getString("vod_play_from");

            String[] play_froms = play_from.split("\\${3}");
            vodAtom.put("vod_play_from", TextUtils.join("$$$", play_froms));

            String[] urls_list = play_url.split("\\${3}"); // 按照$$$和$分割字符串
            ArrayList<String> lPlaygroup = new ArrayList<>();
            for (int i = 0; i < urls_list.length; i++) {

                ArrayList<String> playUrls = new ArrayList<>();
                String[] playUrls_list = urls_list[i].split("#"); // 按照$$$和$分割字符串
                for (int j = 0; j < playUrls_list.length; j++) {
                    playUrls.add( playUrls_list[j].split("\\$")[0] + "$" +  playUrls_list[j].split("\\$")[1]);
                }
                lPlaygroup.add( TextUtils.join("#", playUrls));
            }

            vodAtom.put("vod_play_url", TextUtils.join("$$$", lPlaygroup));
            list.put(vodAtom);
            result.put("list", list);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    @Override
    public String playerContent(String flag, String id, List<String> vipFlags) {
        try {
            if (Misc.isVideoFormat(id)) {
                JSONObject result = new JSONObject();
                result.put("parse", 0);
                result.put("header", "");
                result.put("playUrl", "");
                result.put("url", id);
                return result.toString();
            }
            return "";
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    @Override
    public String searchContent(String key, boolean quick) {
        try {
            String url = siteUrl + "/api.php/provide/vod/?ac=videolist&wd=" + URLEncoder.encode(key);
            String content = OkHttpUtil.string(url, getHeaders(url, key));
            JSONObject dataObject = new JSONObject(decryptResponse(content));
            JSONArray jsonArray = dataObject.getJSONArray("list");
            JSONArray videos = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject vObj = jsonArray.getJSONObject(i);
                String title = vObj.getString("vod_name");
                if (!title.contains(key))
                    continue;
                JSONObject v = new JSONObject();
                v.put("vod_id", vObj.getString("vod_id"));
                v.put("vod_name", title);
                v.put("vod_pic", vObj.getString("vod_pic"));
                v.put("vod_remarks", vObj.getString("vod_remarks"));
                videos.put(v);
            }
            JSONObject result = new JSONObject();
            result.put("list", videos);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    protected String decryptResponse(String src) {
        return src;
    }

    byte[] a(String str) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[333];
        for (int i9 = 0; i9 < 333; i9++) {
            bArr[i9] = (byte) i9;
        }
        if (bytes.length == 0) {
            return null;
        }
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < 333; i12++) {
            i11 = (((bytes[i10] & 255) + (bArr[i12] & 255)) + i11) % 333;
            byte b = bArr[i12];
            bArr[i12] = bArr[i11];
            bArr[i11] = b;
            i10 = (i10 + 1) % bytes.length;
        }
        return bArr;
    }

    byte[] b(byte[] bArr, String str) {
        byte[] a = a(str);
        byte[] bArr2 = new byte[bArr.length];
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < bArr.length; i11++) {
            i9 = (i9 + 1) % 333;
            i10 = ((a[i9] & 255) + i10) % 333;
            byte b = a[i9];
            a[i9] = a[i10];
            a[i10] = b;
            bArr2[i11] = (byte) (a[((a[i9] & 255) + (a[i10] & 255)) % 333] ^ bArr[i11]);
        }
        return bArr2;
    }

    String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    String randomMACAddress() {
        Random rand = new Random();
        byte[] macAddr = new byte[6];
        rand.nextBytes(macAddr);
        macAddr[0] = (byte) (macAddr[0] & (byte) 254);  //zeroing last 2 bytes to make it unicast and locally adminstrated
        StringBuilder sb = new StringBuilder(18);
        for (byte b : macAddr) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    String fakeDid() {
        String i = "";
        String f = "";
        String d = "N/A";
        try {
            d = Build.class.getField("SERIAL").get((Object) null).toString();
        } catch (Exception unused) {
        }
        String e = "";
        try {
            e = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{"ro.build.fingerprint"});
        } catch (Exception unused) {
            return "";
        }
        return (((((((((("" + i) + "||") + f) + "||") + randomMACAddress()) + "||") + randomString(16)) + "||") + d) + "||") + e;
    }

    String md5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return new BigInteger(1, instance.digest()).toString(16);
        } catch (Exception e9) {
            e9.printStackTrace();
            return str;
        }
    }

}
