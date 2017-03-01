package com.jlu.p2p;


import android.os.Handler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by freedom on 2016/12/29 ${hour}: ${minutes}
 * see
 */
public class HttpIpSub implements Runnable {

    private String TargetPath = "http://49.140.162.186:8080/p2p/p2pserver";

    private Handler handler = null;
    private  String   ip = null;
    public HttpIpSub(Handler  handler, String  ip) {
        this.handler = handler;
        this.ip = ip;
    }
    @Override
    public void run() {

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost postMethod = new HttpPost(TargetPath);
        MultipartEntity entity = new MultipartEntity();
        try {
            StringBody ip = new StringBody(this.ip);
            entity.addPart("ip", ip);

            postMethod.setEntity(entity);

            HttpResponse response = httpClient.execute(postMethod);
            HttpEntity httpEntity = response.getEntity();

            String ResultisExisted = EntityUtils.toString(httpEntity, "utf-8");
          //  Log.i("RegisterHttp--", ResultisExisted);
           /* if (ResultisExisted.equals("username_is_registered")) {
                Message msg0 = new Message();
                msg0.what = 0;
                handler.sendMessage(msg0);

            } else if (ResultisExisted.equals("success")) {
                Message msg1 = new Message();
                msg1.what = 1;
                handler.sendMessage(msg1);

            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
