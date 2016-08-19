package ca.tklab.core.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.tklab.core.utils.support.FileInfoVO;
import ca.tklab.core.utils.support.FileNameFilterOnlyExt;



/**
 * @Class Name : FileUtil.java
 * @Description : !!! Describe Class
 * @author SangJoon Kim
 * @since 2012. 4. 30.
 * @version 1.0
 * @see 
 *
 * @Modification Information
 * <pre>
 *    Date                Changer         Comment
 *  ===========    =========    ===========================
 *  2012. 4. 30. by SangJoon Kim: initial version
 * </pre>
 */

public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
    public  static final String CLASS_ROOT_PATH = getClassRootPath();
    public static final String PREFIX_CLASS_FILE = "classpath:" ;
    
    private static String getClassRootPath() {
        try {
//        	return FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            return getResource("/").getFile();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    /**
     * 파일을 삭제 한다.
     * 
     * @param path
     *            폴더명
     * @param fileName
     *            파일명
     * @return 삭제 여부
     */
    public static boolean delFile(String path, String fileName) {
        boolean res = false;

        File file = new File(path, fileName);
        res = file.delete();
        logger.debug("delete file in path : '" + path + "', fileName : '" + fileName + "' is " + res +".") ;
        return res;
    }
    
    
    public static String getExtension(File file) {
        if(file != null  ) {
            return getExtension(file.getName());
        }
        return "";
    }
    /**
     * 파일명의 확장자를 가져옮
     * 
     * @param fileName
     * @return
     */
    public static String getExtension(String fileName) {
        if (fileName.startsWith(".")) {
            return fileName.substring(1);
        }
        String[] tokens = fileName.split("\\.");

        if (tokens.length == 1)
            return "";
        return tokens[(tokens.length - 1)];

    }
    
    
    
    /*******************************************************************************************************************************
     * 디렉토리를 생성한다.
     * <p>
     * @ param path 파일패스
     * <p>
     * @ return true:성공,false:실패
     * <p>
     ******************************************************************************************************************************/
    public static void createDirectory(String path) throws Exception {
        boolean flag = false;
        try {
            File f = new File(path);
            File fp = f.getParentFile();
            if (!fp.exists()) {
                createDirectory(fp.getPath());
            }
            if (!f.exists()) {
                flag = f.mkdir();
                logger.info("Created Directory:" + f.getPath());
                if (!flag) {
                    throw new Exception("Can't make dir : " + path);
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }
    
    /*******************************************************************************************************************************
     * 디렉토리가 있는지 확인한다.
     * <p>
     * @ param path 파일패스
     * <p>
     * @ return true:디렉토리 있음, false:디렉토리없음
     * <p>
     ******************************************************************************************************************************/
    public static boolean existDirectory(String path) {
        boolean i = false;
        try {
            File f = new File(path);
            i = f.exists();
        } catch (Exception e) {
            i = false;
        }
        return i;
    }
    
    public static boolean existFile(String path, String fileName) {
        boolean i = false;
        try {
            File f = new File(path, fileName);
            i = f.exists();
        } catch (Exception e) {
            i = false;
        }
        return i;
    }
    
    /*******************************************************************************************************************************
     * 파일 패스 뒤에 \ 혹은 / 이 없으면 붙여주는 메서드
     * <p>
     * @ param path 파일패스
     * <p>
     * @ param
     * <p>
     * @ return tempp 고쳐진 파일 패스
     * <p>
     ******************************************************************************************************************************/
    public static String chkSeparator(String path) {
        String tempp = "";
        try {
            tempp = path;
            if (!tempp.substring(tempp.length() - 1, tempp.length()).equals(File.separator)) {
                tempp = tempp + File.separator;
            }
        } catch (Exception e) {
        }
        return tempp;
    }
    
    
    /**
     * 대상폴더의 존재여부 확인 후 없으면 폴더를 생성 한다.
     * 
     * @param FilePath
     * @throws FileUtilsException
     */
    public static void CheckAndMakeDir(String FilePath) throws Exception {
        // 파일패스 뒤에 separator가 있는지 확인한다.
        FilePath = chkSeparator(FilePath);
        // 복사할 곳에 디렉토리가 있는지 확인한다.
        if (!existDirectory(FilePath)) {
            // 디렉토리가 없으면 만든다.
            createDirectory(FilePath);
        }
    }
    
    
    public static boolean streamToFile(InputStream fi, File DestFile) {
        boolean chk = false;
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(DestFile);
            if (fi != null && fo != null) {
                byte[] b = new byte[1024];
                int numRead = fi.read(b);
                while (numRead != -1) {
                    fo.write(b, 0, numRead);
                    numRead = fi.read(b);
                }
                fo.flush();
                // logger.info("Moved file="+SrcFile+" outputFile="+DestFile);
                chk = true;
            } else {
                if (DestFile != null) {
                    DestFile.delete();
                }
            }
        } catch (Throwable e) {
            try {
                if (DestFile != null) {
                    DestFile.delete();
                }
            } catch (Throwable ex) {
                logger.error(e.getMessage());
            }

        } finally {
            if (fi != null)
                try {
                    fi.close();
                    fi = null;
                } catch (Exception _ignored) {
                }
            if (fo != null)
                try {
                    fo.close();
                    fo = null;
                } catch (Exception _ignored) {
                }
        }
        return chk;
    }
    
    public static FileInfoVO[] getFileList(String fullPath) {
        return getFileList(fullPath, null);
    }
    /**
     * 해당 폴더의 파일정보를 List에 담아  돌려 준다.
     * 
     * @param fullPath
     * @return FileBean[]
     */
    public static FileInfoVO[] getFileList(String fullPath, String ext) {
        
        if (fullPath == null) {
            return null;
        }
        File path = new File(fullPath);
        FilenameFilter filter = null;
        if( ext != null) {
            filter = new FileNameFilterOnlyExt(ext) ;
        }
        String[] list = path.list(filter);
        List<FileInfoVO> fileList = new ArrayList<FileInfoVO>();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                File file = new File(fullPath , list[i]);
                if (!"unknown".equals(file.getName())) {
                    fileList.add(new FileInfoVO(file));
                }
            }
        }
        return (FileInfoVO[]) fileList.toArray(new FileInfoVO[0]);
    }
    
    /**
     * get absolute filename
     * @param fileName
     * @return
     */
    public static String getAbsoluteFileName(String fileName) {
        String absoluteFileName = null;
        if(fileName != null ) {
            if (fileName.startsWith(PREFIX_CLASS_FILE)) {
                absoluteFileName = CLASS_ROOT_PATH + fileName.substring(PREFIX_CLASS_FILE.length()+1);
                logger.debug(fileName + " changed to  " + absoluteFileName);
            }  else {
                absoluteFileName = fileName;
            }
//            if (!File.separator.equals(absoluteFileName.substring(absoluteFileName.length() - 1))) {
//                absoluteFileName = absoluteFileName + File.separator;
//            }
        }
        
        return absoluteFileName;
    }
    private static ClassLoader getTCL() {
        return Thread.currentThread().getContextClassLoader();
      }

    
    public static URL getResource(String resource) {
        ClassLoader classLoader = null;
        URL url = null;

        try {
          classLoader = getTCL();

          if (classLoader != null) {
//              logger.debug(
//              "Trying to find [" + resource + "] using context classloader "
//              + classLoader + ".");
            url = classLoader.getResource(resource);

            if (url != null) {
              return url;
            }
          }

          // We could not find resource. Ler us now try with the
          // classloader that loaded this class.
          classLoader = FileUtil.class.getClassLoader();

          if (classLoader != null) {
              logger.debug(
              "Trying to find [" + resource + "] using " + classLoader
              + " class loader.");
            url = classLoader.getResource(resource);

            if (url != null) {
              return url;
            }
          }
        } catch (Throwable t) {
          // LogLog.warn(TSTR, t);
        }

        // Last ditch attempt: get the resource from the class path. It
        // may be the case that clazz was loaded by the Extentsion class
        // loader which the parent of the system class loader. Hence the
        // code below.
//        LogLog.debug(
//          "Trying to find [" + resource
//          + "] using ClassLoader.getSystemResource().");

        return ClassLoader.getSystemResource(resource);
      }
    
//    /**
//     * get URL of classRoot 
//     * @return
//     */
//    public static URL getClassRoot() {
//        return getResource("/");
//    }
    
    public static boolean writeFile(File dest, byte[] data, boolean append) {
        boolean result = false;
        BufferedOutputStream bos = null;
        try {
            if (!dest.exists()) {
                dest.getParentFile().mkdirs();
                dest.createNewFile();
            }
            bos = new BufferedOutputStream(new FileOutputStream(dest, append));
            bos.write(data);
            bos.flush();
            result = true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    /**
     * @return
     */
    public static boolean moveFile(File src, File dest) {
        boolean result = false;

        if (src != null && src.exists() && copyFile(src, dest)) {
            src.delete();
            result = true;
        }

        return result;
    }
    /**
     * @return
     */
    public static boolean moveFile(String srcFile, String destFile) {
        return moveFile(new File(srcFile), new File(destFile));
    }
    
    /**
     * Copy File
     * @param SrcFile
     * @param DestFile
     * @return
     */
    public static boolean copyFile(File SrcFile, File DestFile) {
        boolean chk = false;
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(SrcFile);
            chk = streamToFile(fi, DestFile);
        } catch (Throwable e) {
            try {
                if (DestFile != null) {
                    DestFile.delete();
                }
            } catch (Throwable ex) {
            }
            logger.error(e.getMessage());
        } finally {
            if (fi != null) try {
                fi.close();
                fi = null;
            } catch (Exception _ignored) {
            }
        }
        return chk;
    }
}
