package com.base.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.*;

/**
 * @Author: jimmy.xiong
 * @Date: 2020/10/12 9:11
 * @Description:
 */
public class ZipUtil {

    /**
     * 解压文件
     * @param sourceZipFile
     * @param targetFile
     * @throws IOException
     */
    public static void unzipFile(File sourceZipFile,File targetFile) throws IOException {
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }else {
            File [] existFiles = targetFile.listFiles();
            if(existFiles != null && existFiles.length>0){
                for(File f : existFiles){
                    //默默删除指定文件或文件夹及下的内容数据
                    FileUtils.deleteQuietly(f);
                }
            }
        }
        ZipFile zipFile = new ZipFile(sourceZipFile);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()){
            ZipEntry zipEntry = entries.nextElement();
            File unzipFile = new File(targetFile, zipEntry.getName());
            if (zipEntry.isDirectory()){
                // 如果是文件夹，就创建个文件夹
                if (unzipFile.exists()){
                    unzipFile.delete();
                }else {
                    unzipFile.mkdirs();
                }
            }else {
                // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                InputStream in = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                OutputStream out = new BufferedOutputStream(new FileOutputStream(unzipFile));
                byte[] bytes = new byte[1024];
                int count = 0;
                while ((count = in.read(bytes,0,bytes.length)) != -1){
                    out.write(bytes,0,count);
                }
                out.flush();
                out.close();
            }
        }
        zipFile.close();
    }

    /**
     * 压缩文件
     *
     * @param srcDir 压缩源文件路径
     * @param outDir 压缩文件路径
     * @param zipFileName 文件名  xxx.zip
     * @param keepDirStructure  是否保留结构
     */
    public static void zipFile(String srcDir,String outDir,String zipFileName,boolean keepDirStructure) {

        File zipFile = new File(outDir, zipFileName);
        ZipOutputStream zos = null;
        OutputStream out = null;
        try {
            out = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            zip(sourceFile,zos,sourceFile.getName(),keepDirStructure);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (zos!=null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 压缩文件
     * @param srcFileList 需要压缩文件列表
     * @param outDir 压缩文件路径
     * @param zipFileName 文件名  xxx.zip
     * @param keepDirStructure 是否保留结构
     */
    public static void zipFile(List<File> srcFileList,String outDir,String zipFileName,boolean keepDirStructure){

        File zipFile = new File(outDir, zipFileName);
        ZipOutputStream zos = null;
        OutputStream out = null;
        try{
            out = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(out);
            for (File file : srcFileList) {
                zip(file,zos,file.getName(),keepDirStructure);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (zos!=null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    private static void zip(File sourceFile,ZipOutputStream zos,String name,boolean keepDirStructure) throws IOException {

        int bufferSize = 1024;
        byte[] buf = new byte[bufferSize];
        if (sourceFile.isFile()){
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf))!=-1){
                zos.write(buf,0,len);
            }
            zos.closeEntry();
            in.close();

        }else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles ==null || listFiles.length==0){
                if (keepDirStructure){
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            }else {
                for (File file : listFiles) {
                    if (keepDirStructure){
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        zip(file,zos,name+File.separator+file.getName(),keepDirStructure);
                    }else {
                        zip(file,zos,file.getName(),keepDirStructure);
                    }
                }
            }

        }
    }



    public static void main(String[] args) {
       /* String src1 = " ";
        String src2 = " ";
        String out = "C:\\Users\\xiongyang\\Desktop";
        File file1 = new File(src1);
        File file2 = new File(src2);
        List<File> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);
        zipFile(fileList,out,"工作交接.zip",false);
        System.out.println("success");*/

        String zipPath = "E:\\fsdownload\\v1.zip";
        String tarPath = "E:\\fsdownload\\vv1";

        try {
            unzipFile(new File(zipPath),new File(tarPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
