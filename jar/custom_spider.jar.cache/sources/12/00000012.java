package com.github.catvod.spider;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.github.catvod.crawler.Spider;
import com.github.catvod.spider.merge.elQ;
import com.github.catvod.spider.merge.uj;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AppTT extends Spider {
    private String mk = elQ.d("342E352E01115F7D7769464A50671531060F0874790D535A31213D2A0713146F") + Build.VERSION.RELEASE + elQ.d("4B6F") + Build.MODEL + elQ.d("500D2C31041E5F") + Build.ID + elQ.d("59");

    private HashMap<String, String> KZ(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(elQ.d("253C3C2A453B172A372C"), iv(str));
        return hashMap;
    }

    private String iv(String str) {
        return (str.contains(elQ.d("113F307618120060382818")) || str.contains(elQ.d("0828382818")) || str.contains(elQ.d("163D3C3D031B1E"))) ? elQ.d("342E2B2C47485E7E6D78401E113D2D62011559") : (str.contains(elQ.d("0A3C3B")) || str.contains(elQ.d("1624212B")) || str.contains(elQ.d("082E202B")) || str.contains(elQ.d("082C202B")) || str.contains(elQ.d("0335202B")) || str.contains(elQ.d("1437202B")) || str.contains(elQ.d("093B202B")) || str.contains(elQ.d("0121202B"))) ? elQ.d("342E2B2C47485E7E6C78401E113D2D62011559") : str.contains(elQ.d("5E39363C")) ? elQ.d("1F24312C1C0A5F7B7769464A") : elQ.d("342E352E01115F7D7769464A");
    }

    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) {
        String d = elQ.d("06203D071A1F1D2E2B331B");
        String d2 = elQ.d("06203D07181313");
        String d3 = elQ.d("06203D07061B1D2A");
        String d4 = elQ.d("04202D3904");
        String d5 = elQ.d("06203D07011E");
        String d6 = elQ.d("002E3E3D");
        String d7 = elQ.d("1C262A2C");
        try {
            String str3 = elQ.d("183B2D2852555F7E6868464E42616876514F4A786E605055113F3076181200602F69460C1F2B662C110A1572") + str + elQ.d("563F383F0D47") + str2 + elQ.d("563F383F0D0919353C655A4A");
            JSONObject jSONObject = new JSONObject(mk(uj.z(str3, KZ(str3)))).getJSONObject(elQ.d("142E2D39"));
            JSONArray jSONArray = jSONObject.getJSONArray(d7);
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(d5, jSONObject2.getString(d5));
                jSONObject3.put(d3, jSONObject2.getString(d3));
                jSONObject3.put(d2, jSONObject2.getString(d2));
                jSONObject3.put(d, jSONObject2.getString(d));
                jSONArray2.put(jSONObject3);
            }
            JSONObject jSONObject4 = new JSONObject();
            int i2 = jSONObject.getInt(d6);
            int i3 = jSONObject.getInt(d4);
            jSONArray2.length();
            jSONObject4.put(d6, i2);
            jSONObject4.put(elQ.d("002E3E3D0B1505212D"), i3 / 20);
            jSONObject4.put(elQ.d("1C2634311C"), 20);
            jSONObject4.put(d4, i3);
            jSONObject4.put(d7, jSONArray2);
            return jSONObject4.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public String decrypt(String str, String str2, String str3, String str4) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str3.getBytes(elQ.d("251B1F7550")), elQ.d("310A0A"));
            Cipher cipher = Cipher.getInstance(elQ.d("310A0A772B2E226009132B29451F383C0C131E28"));
            cipher.init(2, secretKeySpec, new IvParameterSpec(str4.getBytes()));
            return new String(cipher.doFinal(Base64.decode(str, 0)), str2);
        } catch (Exception unused) {
            return null;
        }
    }

    public String detailContent(List<String> list) {
        String d;
        String d2 = elQ.d("2C6B");
        String d3 = elQ.d("002338210D082F26373E07");
        String d4 = elQ.d("546B7D");
        String d5 = elQ.d("06203D070B151E3B3C361C");
        String d6 = elQ.d("06203D070C13022A3A2C0708");
        String d7 = elQ.d("06203D07091904202B");
        String d8 = elQ.d("06203D071A1F1D2E2B331B");
        String d9 = elQ.d("06203D070908152E");
        String d10 = elQ.d("06203D07111F113D");
        String d11 = elQ.d("06203D07181313");
        String d12 = elQ.d("06203D07011E");
        String d13 = elQ.d("06203D07061B1D2A");
        try {
            String str = elQ.d("183B2D2852555F7E6868464E42616876514F4A786E605055113F3076181200602F69460C1F2B763C0D0E1923353C090E11702F370C25192B64") + list.get(0);
            JSONObject jSONObject = new JSONObject(mk(uj.z(str, KZ(str)))).getJSONObject(elQ.d("142E2D39"));
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject3 = jSONObject.getJSONObject(elQ.d("06263D3D07331E2936"));
            JSONObject jSONObject4 = new JSONObject();
            jSONObject3.getString(d13);
            jSONObject4.put(d12, jSONObject3.getString(d12));
            jSONObject4.put(d13, jSONObject3.getString(d13));
            jSONObject4.put(d11, jSONObject3.getString(d11));
            jSONObject4.put(d10, jSONObject3.getString(d10));
            jSONObject4.put(d9, jSONObject3.getString(d9));
            jSONObject4.put(d8, jSONObject3.getString(d8));
            jSONObject4.put(d7, jSONObject3.getString(d7));
            jSONObject4.put(d6, jSONObject3.getString(d6));
            jSONObject4.put(d5, jSONObject3.getString(d5).trim());
            JSONArray jSONArray2 = jSONObject.getJSONArray(elQ.d("03202C2A0B1F"));
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int i = 0;
            while (true) {
                int length = jSONArray2.length();
                d = elQ.d("1C262A2C");
                if (i >= length) {
                    break;
                }
                JSONArray jSONArray3 = jSONArray2.getJSONObject(i).getJSONArray(d);
                String string = jSONArray2.getJSONObject(i).getJSONObject(d3).getString(elQ.d("0327362F"));
                String string2 = jSONArray2.getJSONObject(i).getJSONObject(d3).getString(elQ.d("002E2B2B0D"));
                ArrayList arrayList = (ArrayList) linkedHashMap.get(string);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(string, arrayList);
                }
                for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                    if (!jSONArray3.get(i2).toString().contains(elQ.d("5E226A2D50")) && !jSONArray3.get(i2).toString().contains(elQ.d("5E22296C"))) {
                        arrayList.add(jSONArray3.get(i2).toString().split(d2)[0] + elQ.d("54") + string2 + jSONArray3.get(i2).toString().split(d2)[1]);
                    }
                    arrayList.add(jSONArray3.get(i2).toString());
                }
                i++;
            }
            String join = TextUtils.join(d4, linkedHashMap.keySet());
            StringBuilder sb = new StringBuilder();
            short size = (short) linkedHashMap.size();
            for (ArrayList arrayList2 : linkedHashMap.values()) {
                size = (short) (size - 1);
                sb.append(TextUtils.join(elQ.d("53"), arrayList2));
                if (size > 0) {
                    sb.append(d4);
                }
            }
            jSONObject4.put(elQ.d("06203D0718161136063E1A151D"), join);
            jSONObject4.put(elQ.d("06203D0718161136062D1A16"), sb.toString());
            jSONArray.put(jSONObject4);
            jSONObject2.put(d, jSONArray);
            return jSONObject2.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public String encrypt(String str, String str2, String str3, String str4) {
        try {
            Cipher cipher = Cipher.getInstance(elQ.d("310A0A772B2E226009132B29451F383C0C131E28"));
            cipher.init(1, new SecretKeySpec(str3.getBytes(), elQ.d("310A0A")), new IvParameterSpec(str4.getBytes()));
            return Base64.encodeToString(cipher.doFinal(str.getBytes(str2)), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public String getToken() {
        return encrypt(Long.valueOf(new Date().getTime()).toString(), elQ.d("251B1F7550"), elQ.d("231D127B0D5F441A002C3D593B261C37420C031F28281A4A130C6D3A103B2118"), elQ.d("1F792A0105175A376C300659022C1A2C"));
    }

    public String homeContent(boolean z) {
        String d = elQ.d("0436293D371411223C");
        String d2 = elQ.d("0436293D371314");
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(d2, 1);
            jSONObject.put(d, elQ.d("97DBECBDD5CB"));
            jSONArray.put(jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(d2, 2);
            jSONObject2.put(d, elQ.d("98F0C7BFD3D795C6FE"));
            jSONArray.put(jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(d2, 3);
            jSONObject3.put(d, elQ.d("95C5F1BED4D1"));
            jSONArray.put(jSONObject3);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put(d2, 4);
            jSONObject4.put(d, elQ.d("97F4E5B0E1C0"));
            jSONArray.put(jSONObject4);
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put(elQ.d("1323382B1B"), jSONArray);
            return jSONObject5.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public String homeVideoContent() {
        String d = elQ.d("06203D071A1F1D2E2B331B");
        String d2 = elQ.d("06203D07181313");
        String d3 = elQ.d("06203D07061B1D2A");
        String d4 = elQ.d("06203D07011E");
        try {
            JSONArray jSONArray = new JSONArray();
            try {
                String d5 = elQ.d("183B2D2852555F7E6868464E42616876514F4A786E605055113F3076181200602F69460C1F2B763B1D081E2E2F311C1F1D23302B1C450436293D3713147269");
                JSONArray jSONArray2 = new JSONObject(mk(uj.z(d5, KZ(d5)))).getJSONObject(elQ.d("142E2D39")).getJSONArray(elQ.d("033830280D083C262A2C"));
                for (int i = 0; i < jSONArray2.length(); i++) {
                    JSONObject jSONObject = jSONArray2.getJSONObject(i);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(d4, jSONObject.getString(d4));
                    jSONObject2.put(d3, jSONObject.getString(d3));
                    jSONObject2.put(d2, jSONObject.getString(d2));
                    jSONObject2.put(d, jSONObject.getString(d));
                    jSONArray.put(jSONObject2);
                }
            } catch (Exception unused) {
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(elQ.d("1C262A2C"), jSONArray);
            return jSONObject3.toString();
        } catch (Throwable unused2) {
            return "";
        }
    }

    public void init(Context context) {
        super.init(context);
    }

    protected String mk(String str) {
        return str;
    }

    public String playerContent(String str, String str2, List<String> list) {
        try {
            boolean contains = str2.contains(elQ.d("5E226A2D50"));
            String d = elQ.d("053D35");
            if (!contains && !str2.contains(elQ.d("5E22296C"))) {
                String str3 = str2 + elQ.d("563C3A65595C0420323D0647") + getToken();
                str2 = new JSONObject(decrypt(uj.z(str3, KZ(str3)), elQ.d("251B1F7550"), elQ.d("231D127B0D5F441A002C3D593B261C37420C031F28281A4A130C6D3A103B2118"), elQ.d("1F792A0105175A376C300659022C1A2C"))).getString(d);
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(elQ.d("002E2B2B0D"), 0);
            jSONObject.put(elQ.d("002338213D081C"), "");
            jSONObject.put(d, str2);
            return jSONObject.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public String searchContent(String str, boolean z) {
        String d = elQ.d("06203D071A1F1D2E2B331B");
        String d2 = elQ.d("06203D07181313");
        String d3 = elQ.d("06203D07061B1D2A");
        String d4 = elQ.d("06203D07011E");
        String d5 = elQ.d("1C262A2C");
        try {
            String str2 = elQ.d("183B2D2852555F7E6868464E42616876514F4A786E605055113F3076181200602F69460C1F2B662F0C47") + URLEncoder.encode(str);
            JSONArray jSONArray = new JSONObject(mk(uj.z(str2, KZ(str2)))).getJSONObject(elQ.d("142E2D39")).getJSONArray(d5);
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(d4, jSONObject.getString(d4));
                jSONObject2.put(d3, jSONObject.getString(d3));
                jSONObject2.put(d2, jSONObject.getString(d2));
                jSONObject2.put(d, jSONObject.getString(d));
                jSONArray2.put(jSONObject2);
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(d5, jSONArray2);
            return jSONObject3.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}