/**
 * 
 */
package utils;

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

	/**
	 * Méthode pour l'upload de fichier sur le disque dur
	 *
	 * @author ryle
	 * @param reques
	 *            : le fichier à uploader
	 *  @return Boolean : si le fichier à la bonne extention alors la méthode
	 *         retourne True, si le fichier n'a pas la bonne extension alors la
	 *         méthode retourne False
	 */
	public Boolean UploadFile(String id, HttpServletRequest request) {
		Boolean validation = false;

		// On prépare les extension de fichier qui seront accepter en upload
		List<String> listOfExtensions = new ArrayList<String>(5);
		listOfExtensions.add("pdf");
		listOfExtensions.add("doc");
		listOfExtensions.add("docx");
		listOfExtensions.add("xls");
		listOfExtensions.add("xlsx");

		// Création du format de la date pour le répertoire de stockage sur le disque
		SimpleDateFormat dateFormatDirectory = new SimpleDateFormat("yyyy");
		String dateDirectory = dateFormatDirectory.format(new Date());
		// Création du format de la date pour le fichier, il sera donc renomer
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy_MM_dd-HH-mm-ss");
		String date = dateFormat.format(new Date());

		// on prépare pour l'envoie par la mise en oeuvre en mémoire
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();

		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			List<FileItem> items = (List<FileItem>) uploadHandler
					.parseRequest(request);

			Iterator<FileItem> itr = (Iterator<FileItem>) items.iterator();
			// au besoin on creer le dossier principal des archive et le sous
			// dossier qui correspond à l'année en cour
			new File(Constant.dossierUplaod + dateDirectory).mkdirs();

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();

				// on récupère l'extension du fichier qui est uploader
				String fileExtensionName = item.getName();
				fileExtensionName = FilenameUtils
						.getExtension(fileExtensionName);

				// on donne le chemin d'écriture au fichier ainsi que sont nom
				// et on le met dans l'année en cours
				File file = new File(Constant.dossierUplaod + dateDirectory, id + "_"
						+ date + "." + fileExtensionName);

				// on vérifie si les extensions des fichier uploader sont
				// acceptée ou pas
				if (listOfExtensions.contains(fileExtensionName)) {
					// on écrit le fichier sur le disque dur
					item.write(file);

					// on écrit les informations de l'upload dans la db
					AddDocumentLinkDAO documentLink = new AddDocumentLinkMySqlImpl();

					try {
						documentLink.addDocumentLink(file.getName(),

								//on récupère le chemin absolue du fichier

								file.getAbsolutePath());

						// on valide pour la valeur de retour;
						validation = true;
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					log.error("L'extention du fichier n'est pas valide");
				}
			}
		}
		catch (FileUploadException ex) {
			ex;
		}

		catch (Exception ex) {
			ex;
		}
		return validation;
	}
}