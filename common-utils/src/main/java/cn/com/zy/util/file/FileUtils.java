package cn.com.zy.util.file;

import java.io.File;

/**
 * @description: <p>文件操作帮助类</p>
 */
public class FileUtils {
    public static String addSeparator(String path) {
        if (path.endsWith(File.separator)) {
            return path;
        }
        return path + File.separator;
    }
}
