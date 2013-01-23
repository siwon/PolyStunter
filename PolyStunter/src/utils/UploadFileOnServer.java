
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author Alexandre Bisiaux
 *
 */
public class UploadFileOnServer {

	public static List<String> log = new ArrayList<String>();

	public String uploadFile(String filename, HttpServletRequest request) {

		List<String> listOfExtensions = new ArrayList<String>(4);
		listOfExtensions.add("jpg");
		listOfExtensions.add("jpeg");
		listOfExtensions.add("png");
		listOfExtensions.add("gif");
		
		String newName = "";

		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();

		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		List<FileItem> items = (List<FileItem>) uploadHandler.parseRequest(request);

		Iterator<FileItem> itr = (Iterator<FileItem>) items.iterator();

		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();

			// on récupère l'extension du fichier qui est uploader
			String fileExtensionName = item.getName();
			fileExtensionName = FilenameUtils.getExtension(fileExtensionName);
			
			newName = filename + fileExtensionName;
			
			File file = new File("/products/"+ newName);

			// on vérifie si les extensions des fichier uploader sont
			// acceptée ou pas
			if (listOfExtensions.contains(fileExtensionName)) {
				// on écrit le fichier sur le disque dur
				item.write(file);
			}
			else {
				throw(new Exception("Erreur d'extension"));
			}
		}
		return newName;
	}
}