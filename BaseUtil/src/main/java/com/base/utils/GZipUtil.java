package com.base.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * @Author: jimmy.xiong
 * @Date: 2020/10/12 14:40
 * @Description:
 */
public class GZipUtil {
    public static final int BUFFER = 1024;
    public static final String EXT_GZ = ".gz";
    public static final String EXT_TAR_GZ = ".tar.gz";

    public static void unTarGz(String inFileName,String targetFile) throws IOException {
        /*GZIPInputStream in = null;
        try {
            in = new GZIPInputStream(new FileInputStream(inFileName));
        } catch(FileNotFoundException e) {
            System.err.println("File not found. " + inFileName);
        }
        String outFileName = getFileName(inFileName);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(targetFile);
        } catch (FileNotFoundException e) {
            System.err.println("Could not write to file. " + outFileName);
            System.exit(1);
        }

        byte[] buf = new byte[1024];
        int len;
        while((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        System.out.println("Closing the file and stream");
        in.close();
        out.close();*/

    }

    public static String getFileName(String f) {
        String fname = "";
        int i = f.lastIndexOf('.');

        if (i > 0 &&  i < f.length() - 1) {
            fname = f.substring(0,i);
        }
        return fname;
    }

    public static void unGZip(File gzipFile,String outPath,boolean delete) throws Exception {

        String fileName = "";
        if (gzipFile.getName().contains(EXT_TAR_GZ)){
            fileName = gzipFile.getName().replace(EXT_TAR_GZ,"");
        }else if (gzipFile.getName().contains(EXT_GZ)){
            fileName = gzipFile.getName().replace(EXT_GZ,"");
        }

        if (outPath==null || "".equals(outPath)){
            int i = gzipFile.getAbsolutePath().lastIndexOf(File.separator);
            outPath = gzipFile.getAbsolutePath().substring(0,i);
        }

        File file = new File(outPath, fileName);
        if (file.exists()){
            FileUtils.cleanDirectory(file);
        }else {
            file.mkdirs();
        }
        InputStream fis = new FileInputStream(gzipFile);
        OutputStream fos = new FileOutputStream(file);
        decompress(fis,fos);
        fis.close();
        fos.flush();
        fos.close();
        if (delete) {
            gzipFile.delete();
        }
    }

    public static void decompress(InputStream is, OutputStream os) throws Exception {

        GZIPInputStream gis = new GZIPInputStream(is);

        int count;
        byte data[] = new byte[BUFFER];
        while ((count = gis.read(data, 0, BUFFER)) != -1) {
            os.write(data, 0, count);
        }
        gis.close();
    }

    public static void main(String[] args) {
        String gz = "E:\\fsdownload\\node-v4.1.0-linux-x64.tar.gz";
        String files = "C:\\Users\\xiongyang\\Desktop\\eee";
        File file = new File(gz);
        try {
            unTarGz(gz,files);
            //unGZip(file,files,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
