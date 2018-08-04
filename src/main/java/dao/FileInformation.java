package dao;

public class FileInformation {
    private String fileName;
    private String fileMimeType;
    private long fileSize;
    private String fileExtension;


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileMimeType(String fileMimeType) {
        this.fileMimeType = fileMimeType;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileName(){
        return fileName;
    }

    public String getFileMimeType(){
        return fileMimeType;
    }

    public long getFileSize(){
        return fileSize;
    }

    public String getFileExtension(){
        return fileExtension;
    }
}
