package utils;

import org.apache.commons.fileupload.FileItem;
import exceptions.ExtensionException;
import java.io.File;
import java.util.*;

/**
 * @author Alexandre Bisiaux
 *
 */
public class UploadFileOnServer {

	public static List<String> log = new ArrayList<String>();

	public String uploadFile(FileItem fileItem, String root, String name) throws ExtensionException {

		List<String> listOfExtensions = new ArrayList<String>(4);
		listOfExtensions.add("jpg");
		listOfExtensions.add("jpeg");
		listOfExtensions.add("png");
		listOfExtensions.add("gif");
		
		File path = new File(root);
		
		if (!path.exists()) {
            path.mkdirs();
        }
		String fileName = fileItem.getName();
		String ext = getExtension(fileName);
		String newName = name + "." + ext;
		File file = new File(path + "/" + newName);

		if (listOfExtensions.contains(ext))
			try {
				fileItem.write(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			throw(new ExtensionException("Erreur d'extension du fichier."));
		
		return newName;
	}
	
	public String getExtension(String filename) {
		int id = filename.lastIndexOf(".");
		return filename.substring(id+1,filename.length()); 
	}
	
	public static boolean removeFile(String path){
		File file = new File(path);
		return file.delete();
	}
}