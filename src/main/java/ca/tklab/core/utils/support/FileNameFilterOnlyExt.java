package ca.tklab.core.utils.support;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @Class Name : FileNameFilterOnlyExt.java
 * @Description : !!! Describe Class
 * @author SangJoon Kim
 * @since 2012. 5. 15.
 * @version 1.0
 * @see
 * 
 * @Modification Information
 * 
 *               <pre>
 *    Date                Changer         Comment
 *  ===========    =========    ===========================
 *  2012. 5. 15. by SangJoon Kim: initial version
 * </pre>
 */

public class FileNameFilterOnlyExt implements FilenameFilter {
    String ext;

    public FileNameFilterOnlyExt(String ext) {
        if (ext != null) {
            this.ext = "." + ext.toLowerCase();
        }
    }

    public boolean accept(File dir, String name) {
        String lowercaseName = name.toLowerCase();
        return lowercaseName.endsWith(ext);
    }
}