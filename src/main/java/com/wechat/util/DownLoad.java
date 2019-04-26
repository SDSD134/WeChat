package com.wechat.util;

import org.aspectj.util.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DownLoad {
    //获取当前项目的绝对路径
    public static String getTomcatPath(){
        String nowpath;
        String tempdir;
        nowpath=System.getProperty("user.dir");
        tempdir=nowpath.replace("bin", "");  //把bin 文件夹变到 webapps文件里面
        //把bin 文件夹变到 webapps文件里面
        return tempdir;
    }

    /**
     * 将图片下载下来后，上传到OSS
     * @param imgLink
     * @param downloadPath
     * @return
     * @throws Exception
     */
    /*private String downloadImagAndUploadToOss(String imgLink,String downloadPath,String imgName) throws Exception{
        List<String> urlList=new ArrayList<String>();
        urlList.add(imgLink);
      //  String imgName=DateUtil.formatDate(new Date(), "yyyyMMddhhmmss")+UuidUtil.createUUID()+".jpg";
        downloadPicture(urlList,downloadPath,imgName);
        String key="userAlbum/"+imgName;
        String imgUrl=OSSObjectAPI.genOssPicUrl(OSSObjectAPI.XI_AN_BUCKET_NAME,OSSObjectAPI.XIAN_ACCESS_ID,OSSObjectAPI.XIAN_ACCESS_KEY,
                "http://oss-cn-zhangjiakou.aliyuncs.com/",downloadPath+imgName,key);
        FileUtil.delete(downloadPath+imgName);
        return imgUrl;
    }
    *//**
     * 传入要下载的图片的url列表，将url所对应的图片下载到本地
     * @param urlList
     * @throws Exception
     *//*
    private void downloadPicture(List<String> urlList,String path,String imgName) throws Exception {
        if(urlList==null||urlList.size()==0){
            return;
        }
        URL url = null;
        FileOutputStream fileOutputStream =null;
        InputStream inputStream =null;
        for (String urlString : urlList) {
            try {
                url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0");
                connection.setConnectTimeout(10 * 1000);
                connection.setReadTimeout(15 * 1000);
                inputStream = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int length;
                fileOutputStream= new FileOutputStream(path+ File.separator+ imgName);
                while ((length = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                inputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

*/


    }
