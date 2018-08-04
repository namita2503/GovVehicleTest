package util;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final Logger logger = Logger.getLogger(FileUtil.class);

    public static enum MimeType {
        TEXT_PLAIN("text/plain"), APPLICATION_EXCEL("application/excel"), TEXT_CSV("text/csv"), APPLICATION_MSWORD("application/msword"), APPLICATION_IMAGE(
                "application/image"), APPLICATION_XML("application/xml"), APPLICATION_JSON("application/json");

        private String mimeTypeDesc;

        private MimeType(String mimeTypeDesc) {
            this.mimeTypeDesc = mimeTypeDesc;
        }

        public String toString() {
            return this.mimeTypeDesc;
        }
    }

    private static String reteriveMimeType(File file) {
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        mimeTypesMap.addMimeTypes(MimeType.TEXT_CSV + " csv");
        mimeTypesMap.addMimeTypes(MimeType.APPLICATION_EXCEL + " xlsx xls");
        mimeTypesMap.addMimeTypes(MimeType.TEXT_PLAIN + " txt");
        mimeTypesMap.addMimeTypes(MimeType.APPLICATION_JSON + " json");
        mimeTypesMap.addMimeTypes(MimeType.APPLICATION_XML + " xml");
        mimeTypesMap.addMimeTypes(MimeType.APPLICATION_IMAGE + " jpg png jpeg");
        mimeTypesMap.addMimeTypes(MimeType.APPLICATION_MSWORD + " docx doc");
        return mimeTypesMap.getContentType(file);
    }

    public static File[] reteriveAllItems(String directoryName){
        File directory = new File(directoryName);
        if (directory.isDirectory()) {
            File[] fList = directory.listFiles();
            return fList;
        }
        logger.debug("Directory is invalid to reterive all items");
        return null;
    }

    public static List<File> reteriveOnlyFiles(String directoryName){
        List<File> listOfFiles = new ArrayList<File>();
        File directory = new File(directoryName);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file: files) {
                if (file.isFile()) {
                    listOfFiles.add(file);
                } else {
                    logger.debug("Folder <<" + file.getName() + ">> found");
                }
            }
            if (listOfFiles.size()>0) {
                return listOfFiles;
            } else {
                logger.debug("No file found in Directory");
                return null;
            }
        }
        logger.debug("Directory is invalid to reterive files");
        return null;
    }

    public static String getFileMimeType(File file){
        if (file.isFile()) {
            return reteriveMimeType(file);
        }
        logger.debug("File is invalid to get mime type");
        return null;
    }

    public static String getFileExtension(File file){
        if (file.isFile()) {
            return FilenameUtils.getExtension(file.getName());
        }
        logger.debug("File is invalid to get extension");
        return null;
    }
}
