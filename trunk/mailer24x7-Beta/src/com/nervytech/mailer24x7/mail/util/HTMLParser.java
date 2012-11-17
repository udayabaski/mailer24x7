package com.nervytech.mailer24x7.mail.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HTMLParser {


	private static Pattern EXTRACT_TEXT_PATTERN_1 = Pattern.compile ("\\s+");  

	private static Pattern EXTRACT_TEXT_PATTERN_2 = Pattern.compile ("<!--.*?-->");  

	private static Pattern EXTRACT_TEXT_PATTERN_3 = Pattern.compile ("<head>.*?</head>");  

	private static Pattern EXTRACT_TEXT_PATTERN_4 = Pattern.compile ("<br.*?>");  

	private static Pattern EXTRACT_TEXT_PATTERN_5 = Pattern.compile ("</li>");  

	private static Pattern EXTRACT_TEXT_PATTERN_6 = Pattern.compile ("</div>");  

	private static Pattern EXTRACT_TEXT_PATTERN_7 = Pattern.compile ("</p>");  

	private static Pattern EXTRACT_TEXT_PATTERN_8 = Pattern.compile ("</tr>");  

	private static Pattern EXTRACT_TEXT_PATTERN_9 = Pattern.compile ("</h[1-6]>");  

	private static Pattern EXTRACT_TEXT_PATTERN_10 = Pattern.compile ("</t[dh]>");  

	private static Pattern EXTRACT_TEXT_PATTERN_11 = Pattern.compile ("<.*?>");  

	private static Pattern EXTRACT_TEXT_PATTERN_12 = Pattern.compile ("^\\s+");  

	private static Pattern EXTRACT_TEXT_PATTERN_13 = Pattern.compile ("\\s+$");  

	private static Pattern EXTRACT_TEXT_PATTERN_14 = Pattern.compile ("&nbsp;");  

	private static Pattern EXTRACT_TEXT_PATTERN_16 = Pattern.compile ("&#160;");  
	
	private static Pattern EXTRACT_HREF_PATTERN = Pattern.compile ("href=\"",Pattern.CASE_INSENSITIVE);

	public static final int EXTRACTTEXT_1 = 101;

	public static final int EXTRACTTEXT_2 = 102;

	public static final int EXTRACTTEXT_3 = 103;

	public static final int EXTRACTTEXT_4 = 104;

	public static final int EXTRACTTEXT_5 = 105;

	public static final int EXTRACTTEXT_6 = 106;

	public static final int EXTRACTTEXT_7 = 107;

	public static final int EXTRACTTEXT_8 = 108;

	public static final int EXTRACTTEXT_9 = 109;

	public static final int EXTRACTTEXT_10 = 110;

	public static final int EXTRACTTEXT_11 = 111;

	public static final int EXTRACTTEXT_12 = 112;

	public static final int EXTRACTTEXT_13 = 113;

	public static final int EXTRACTTEXT_14 = 114;

	public static final int EXTRACTTEXT_15 = 115;

	public static final int EXTRACTTEXT_16 = 116;


	public static String replaceWithS3Url(CharSequence content,String replaceWith) {
		
		if (content == null)
		{
			return null;
		}
		
		return replaceAll (EXTRACT_HREF_PATTERN, content, "href=\"https://s3-ap-southeast-1.amazonaws.com/").toString();
		
	}

	public static String extractText(CharSequence content) {

		if (content == null)
		{
			return null;
		}

		content = parse (EXTRACTTEXT_1, content);

		content = parse (EXTRACTTEXT_2, content);

		content = parse (EXTRACTTEXT_3, content);

		content = parse (EXTRACTTEXT_4, content);

		content = parse (EXTRACTTEXT_5, content);

		content = parse (EXTRACTTEXT_6, content);

		content = parse (EXTRACTTEXT_7, content);

		content = parse (EXTRACTTEXT_8, content);

		content = parse (EXTRACTTEXT_9, content);

		content = parse (EXTRACTTEXT_10, content);

		content = parse (EXTRACTTEXT_11, content);

		content = parse (EXTRACTTEXT_12, content);

		content = parse (EXTRACTTEXT_13, content);

		content = parse (EXTRACTTEXT_14, content);

		content = parse (EXTRACTTEXT_15, content);

		content = parse (EXTRACTTEXT_16, content);

		return content.toString();

	}



	public static CharSequence replaceAll (Pattern matchPattern, CharSequence inputStr, String with)
	{
		Matcher mat1 = matchPattern.matcher (inputStr);

		boolean result = mat1.find();
		if (result) {
			StringBuffer sb = new StringBuffer();
			do {
				mat1.appendReplacement(sb, with);
				result = mat1.find();
			} while (result);
			mat1.appendTail(sb);
			return sb;
		}
		return inputStr;
	}




	public static CharSequence replaceFirst (Pattern matchPattern, CharSequence inputStr, String with)
	{
		Matcher mat1 = matchPattern.matcher (inputStr);
		mat1.find();

		StringBuffer sb = new StringBuffer();
		if (mat1.find())
		{
			mat1.appendReplacement(sb, with);
		}
		mat1.appendTail(sb);
		return sb;
	}



	public static CharSequence parse(int operation, CharSequence inputStr){

		if (operation == EXTRACTTEXT_1)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_1, inputStr, " ");
		}

		else if (operation == EXTRACTTEXT_2)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_2, inputStr, "");
		}
		else if (operation == EXTRACTTEXT_3)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_3, inputStr, "");
		}
		else if (operation == EXTRACTTEXT_4)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_4, inputStr, "\n");
		}
		else if (operation == EXTRACTTEXT_5)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_5, inputStr, "\n");
		}
		else if (operation == EXTRACTTEXT_6)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_6, inputStr, "\n");
		}
		else if (operation == EXTRACTTEXT_7)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_7, inputStr, "\n");
		}
		else if (operation == EXTRACTTEXT_8)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_8, inputStr, "\n");
		}
		else if (operation == EXTRACTTEXT_9)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_9, inputStr, "\n");
		}
		else if (operation == EXTRACTTEXT_10)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_10, inputStr, " ");
		}
		else if (operation == EXTRACTTEXT_11)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_11, inputStr, "");
		}
		else if (operation == EXTRACTTEXT_12)
		{
			return replaceFirst (EXTRACT_TEXT_PATTERN_12, inputStr, "");
		}
		else if (operation == EXTRACTTEXT_13)
		{
			return replaceFirst (EXTRACT_TEXT_PATTERN_13, inputStr, "\n");
		}
		else if (operation == EXTRACTTEXT_14)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_14, inputStr, " ");
		}
		else if (operation == EXTRACTTEXT_15)
		{
			return replaceFirst (EXTRACT_TEXT_PATTERN_16, inputStr, "\t");
		}
		else if (operation == EXTRACTTEXT_16)
		{
			return replaceAll (EXTRACT_TEXT_PATTERN_16, inputStr, "");
		}

		return inputStr;

	}
	
	/*public static void main(String args[]) throws IOException{
		File s = new File("D:/temp.html");
		Reader input = new FileReader(new InputSt);
		StringWriter output = new StringWriter();
		try {
		  IOUtils.copy(input, output);
		} finally {
		  input.close();
		}
		String fileContents = output.toString();
		System.out.println("FileContentttttt "+fileContents);
		
		String out = replaceWithS3Url(fileContents);
		System.out.println("OOOOOOOOOOOOOOOOOOO "+out);

	}*/

}