package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.bea.wlw.runtime.core.config.WlwManifestDocument.WlwManifest.Project;

/**
 * @author Toby 通用工具类
 */
public class Utils {

    /**
     * @param args
     * @throws Exception
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO Auto-generated method stub
        // File file = new File("D:/user/mms.xml");
        // System.out.println(file.renameTo(new File("D:/user/mms5.xml")));

        // 1
        // compress("D:/user/test", "D:/user/test.zip");

        /*
         * String fileName = "D:/user/88.zip"; try {
         * System.out.println(encryptBASE64(readFileToBytes(fileName))); } catch
         * (Exception e) { // TODO Auto-generated catch block
         * e.printStackTrace(); }
         */

        /*
         * String mi
         * ="UEsDBBQACAA";
         * RandomAccessFile inOut = new RandomAccessFile(
         * "D:/user/sample.","rw"); inOut.write(decryptBASE64(mi));
         * inOut.close();
         */

        // System.out.println(new String(decryptBASE64("5rWL6K+V"),"utf-8"));
        // 2
        // String xml =
        // createXML("1828","qww","123456","10658103619033","15918542546",encryptBASE64("两款茶饮润肺护肤防过敏".getBytes()),encryptBASE64(readFileToBytes("D:/user/test.zip")));
        // System.out.println(xml);
        /*
         * String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"
         * standalone=\"yes\"?><TaskDataTransfer4ERsp
         * xmlns=\"http://www.aspirehld.com/iecp/TaskDataTransfer4ERsp\"><ResultCode>2000</ResultCode><TaskId></TaskId><ResultMSG>没有获得IP鉴权!</ResultMSG></TaskDataTransfer4ERsp>";
         * 
         * Document doc = DocumentHelper.parseText(xml); // 将字符串转为XML Element
         * rootElt = doc.getRootElement(); // 获取根节点
         * 
         * String resultCode = rootElt.element("ResultCode").getTextTrim();
         * String TaskId = rootElt.element("TaskId").getTextTrim(); String
         * ResultMSG = rootElt.element("ResultMSG").getTextTrim();
         * System.out.println(" "+resultCode+" "+TaskId+" "+ResultMSG);
         */

    }

    /**
     * BASE64解密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * 获取路径下所有文件名
     * 
     * @param path
     * @return
     */
    public static String[] getFile(String path) {
        File file = new File(path);
        String[] name = file.list();
        return name;
    }

    /**
     * 
     * @param sourceDirPath
     * @param targetDirPath
     * @throws IOException
     */
    public static void copyDir(String sourceDirPath, String targetDirPath) throws IOException {
        // 创建目标文件夹
        (new File(targetDirPath)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDirPath)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 复制文件
                String type = file[i].getName().substring(file[i].getName().lastIndexOf(".") + 1);


				FileUtil.copyFile(file[i], new File(targetDirPath + file[i].getName()));
            }
            if (file[i].isDirectory()) {
                // 复制目录
                String sourceDir = sourceDirPath + File.separator + file[i].getName();
                String targetDir = targetDirPath + File.separator + file[i].getName();
                FileUtil.copyDirectiory(sourceDir, targetDir);
            }
        }
    }

    /**
     * 读取文件中内容
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static String readFileToString(String path) throws IOException {
        String resultStr = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            byte[] inBuf = new byte[2000];
            int len = inBuf.length;
            int off = 0;
            int ret = 0;
            while ((ret = fis.read(inBuf, off, len)) > 0) {
                off += ret;
                len -= ret;
            }
			// resultStr = new String(new String(inBuf, 0, off,
			// MTOServerConstants.CODE_GBK).getBytes());
            resultStr = new String(new String(inBuf, 0, off));
        } finally {
            if (fis != null)
                fis.close();
        }
        return resultStr;
    }

    /**
     * 文件转成字节数组
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] readFileToBytes(String path) throws IOException {
        byte[] b = null;
        InputStream is = null;
        File f = new File(path);
        try {
            is = new FileInputStream(f);
            b = new byte[(int) f.length()];
            is.read(b);
        } finally {
            if (is != null)
                is.close();
        }
        return b;
    }

    /**
     * 将byte写入文件中
     * 
     * @param fileByte
     * @param filePath
     * @throws IOException
     */
    public static void byteToFile(byte[] fileByte, String filePath) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(filePath));
            os.write(fileByte);
            os.flush();
        } finally {
            if (os != null)
                os.close();
        }
    }

    /**
     * 判空字串
     * 
     * @param str
     * @return 为空true
     */
    public static boolean strIsNull(String str) {
        return str == null || str.equals("");
    }

    /**
     * 折分数组
     * 
     * @param ary
     * @param subSize
     * @return
     */
    public static List<List<Object>> splitAry(Object[] ary, int subSize) {
        int count = ary.length % subSize == 0 ? ary.length / subSize : ary.length / subSize + 1;

        List<List<Object>> subAryList = new ArrayList<List<Object>>();

        for (int i = 0; i < count; i++) {
            int index = i * subSize;

            List<Object> list = new ArrayList<Object>();
            int j = 0;
            while (j < subSize && index < ary.length) {
                list.add(ary[index++]);
                j++;
            }

            subAryList.add(list);
        }

        return subAryList;
    }

    /**
     * @param mobile
     * @return
     */
    public static String ArrayToString(Object[] mobile) {
        String destId = "";
        for (Object phone : mobile) {
            destId += " " + (String) phone;
        }
        return destId.trim();
    }
}