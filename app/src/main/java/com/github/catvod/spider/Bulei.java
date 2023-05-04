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

    protected HashMap<String, String> getHeaders(String url) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("method", "GET");
        headers.put("Host", siteUrl);
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        return headers;
    }

    @Override
    public String homeContent(boolean filter) {
        try {
            String url = siteUrl + "/api.php/provide/vod/";
            String content = OkHttpUtil.string(url, getHeaders(url));
            JSONObject dataObject = new JSONObject(content);
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
            String content = OkHttpUtil.string(url, getHeaders(url));
            JSONObject dataObject = new JSONObject(content);
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
            String content = OkHttpUtil.string(url, getHeaders(url));
            JSONObject dataObject = new JSONObject(content);
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
            String content = OkHttpUtil.string(url, getHeaders(url));
            JSONObject dataObject = new JSONObject(content);
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
            String content = OkHttpUtil.string(url, getHeaders(url));
            JSONObject dataObject = new JSONObject(content);
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

}
