package file;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRenamer {

	public static void main(String ...args) {
		
		File directory = new File("/home/ketav/Videos/Naruto");
		if(directory.isDirectory()) {
			Pattern filenamePattern = Pattern.compile("[0-9]{3}");
			Pattern extensionPattern = Pattern.compile("\\.(.)*$");
			File[] files = directory.listFiles();
			for(File file : files) {
				Matcher filenameMatcher = filenamePattern.matcher(file.getName());
				Matcher extensionMatcher = extensionPattern.matcher(file.getName());
				if(filenameMatcher.find() && extensionMatcher.find()) {
					String filename = String.format("%s/%s%s", file.getParent(), 
							filenameMatcher.group(), extensionMatcher.group());
					System.out.println(file.getAbsolutePath()+" -> "+filename);
					file.renameTo(new File(filename));
				}
			}
		}
	}
	
}
