package com.chj.paike.service.impl;

import com.chj.paike.service.IJsoupService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsoupServiceImpl implements IJsoupService {

    private final String GET_XM_URL="http://jwzx.cqupt.edu.cn/jwzxtmp/data/json_StudentSearch.php?searchKey=";
    private final String GET_XM_URL_OUT="http://jwzx.cqupt.congm.in/jwzxtmp/data/json_StudentSearch.php?searchKey=";
    private final String GET_KB_URL="http://jwzx.cqupt.edu.cn/jwzxtmp/kebiao/kb_stu.php?xh=";
    private final String GET_KB_URL_OUT="http://jwzx.cqupt.congm.in/jwzxtmp/kebiao/kb_stu.php?xh=";

    /**
     * 根据学号获取学生姓名
     * @param xh
     * @return
     */
    @Override
    public String getName(String xh) {

        try {
            String body = Jsoup.connect(GET_XM_URL_OUT+xh)
                            .userAgent("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)").
                            ignoreContentType(true).
                            timeout(5000).execute().
                            body();
            JSONObject json = JSONObject.fromObject(body);
            JSONArray jsonArray = json.getJSONArray("returnData");
            String name = jsonArray.getJSONObject(0).getString("xm");
            return name;

        } catch (IOException e) {
            e.printStackTrace();
        }

       /* try {

            System.getProperties().setProperty("http.proxyHost", "221.237.155.64");
            System.getProperties().setProperty("http.proxyPort", "9797");
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            URL url = new URL(GET_XM_URL+"xh");
            HttpURLConnection urlcon = (HttpURLConnection)url.openConnection(proxy);
            urlcon.connect();         //获取连接
            InputStream is = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
            StringBuffer bs = new StringBuffer();
            String l = null;
            while((l=buffer.readLine())!=null){
                bs.append(l);
            }
            System.out.println(bs.toString());
            Document doc = Jsoup.parse(bs.toString());
            return doc.body().toString();


        } catch (Exception e) {

            e.printStackTrace();

        }*/

   return  null;
    }

    @Override
    public List<Integer> getFreeDutyTimes(String xh){

        List<Integer> freeDutyTimes = new ArrayList<>();
        try {
            Document document = Jsoup.connect(GET_KB_URL_OUT + xh).timeout(5000).get();
            Elements elements = document.getElementsByTag("tr");
            /**
             * 1.2节 index-->1（暂不计入）
             * 3.4节 index-->2
             * 5.6节 index-->4
             * 7.8节 index-->5
             * 9.10节 index-->7
             */
            int a[]={2,4,5,7};

            int time;//时间段名称
            int circle=0;//循环

            for (int index: a) {
                Element element = elements.get(index);
                Elements tds= element.getElementsByTag("td");
                if(index == 7){
                    for(int i=1;i<=4;i++){
                        Element td = tds.get(i);
                        if(td.text().equals("")){
                            freeDutyTimes.add(circle*5+i);
                        }
                    }
                }else{
                    for(int i=1;i<=5;i++){
                        Element td = tds.get(i);
                        if(td.text().equals("")){
                            freeDutyTimes.add(circle*5+i);
                        }
                    }
                }
                circle++;
            }
            System.out.println(freeDutyTimes.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return freeDutyTimes;
    }

}
