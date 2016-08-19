package ca.tklab.core.utils.support;

import java.io.File;
import java.util.Date;

/**
 * @Class Name : FileInfoVO.java
 * @Description : !!! Describe Class
 * @author SangJoon Kim
 * @since 2012. 5. 9.
 * @version 1.0
 * @see 
 *
 * @Modification Information
 * <pre>
 *    Date                Changer         Comment
 *  ===========    =========    ===========================
 *  2012. 5. 9. by SangJoon Kim: initial version
 * </pre>
 */

public class FileInfoVO extends BaseVO {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String filename = null;

    private String fullFileName = null;

    private long size = -1;

    private long lastModified = -1;

    private boolean isDirectory = false;;

    public FileInfoVO(File file) {
        this.lastModified = file.lastModified();
        this.size = file.length();
        this.filename = file.getName();
        this.fullFileName = file.getAbsolutePath();
        if (file.isDirectory()) {
            this.isDirectory = true;
        }
    }

    public Date getLastModifiedDaTe() {
        return new Date(this.lastModified);
    }
    /**
     * @return
     */
    public long getLastModified() {
        return this.lastModified;
    }

    /**
     * 경로없이 파일명만 돌려 준다.
     * 
     * @return
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @return
     */
    public long getSize() {
        return size;
    }


    public boolean isDirectory() {
        return isDirectory;
    }

    /**
     * 경로를 포함한 파일명을 돌려준다.
     * 
     * @return Returns the pathname.
     */
    public String getFullFileName() {
        return fullFileName;
    }

}