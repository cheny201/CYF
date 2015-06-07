package test.file.replace;

import java.io.File;
import java.io.IOException;

import com.cyf.util.file.FileUtil;

public class Test {
	
	public static void main(String[] args) throws IOException {
		String path = "F:\\Workspace\\Eclipse\\CYF\\test\\test\\file\\replace";
		FileUtil.replaceFiles("test", "cc", new String[]{".txt"}, new File(path), false);
	}

}
